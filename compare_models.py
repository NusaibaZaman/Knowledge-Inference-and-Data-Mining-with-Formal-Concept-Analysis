import sys
import os
import tempfile
import subprocess
import numpy as np
import pandas as pd
from sklearn.model_selection import StratifiedKFold
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn.dummy import DummyClassifier
from sklearn.metrics import accuracy_score, recall_score, roc_auc_score

import binarise

method = sys.argv[1] if len(sys.argv) > 1 else "clinical"
jar = sys.argv[2] if len(sys.argv) > 2 else "target/fca-skeleton-1.0-jar-with-dependencies.jar"
min_support, min_confidence = 30, 0.70

heart = pd.read_csv("heart_clean.csv")
y = heart["target"].astype(int)
x = heart.drop(columns=["target"])
x.index = ["p" + str(i) for i in range(len(x))]
y.index = x.index

def write_context(path, binary):
    with open(path, "w") as f:
        f.write(";" + ";".join(binary.columns) + "\n")
        for patient, row in binary.iterrows():
            cells = ["X" if v == 1 else "" for v in row]
            f.write(patient + ";" + ";".join(cells) + "\n")

models = {
    "majority baseline": DummyClassifier(strategy="most_frequent"),
    "logistic regression": LogisticRegression(max_iter=1000),
    "random forest": RandomForestClassifier(random_state=0),
}

metrics = ["accuracy", "recall", "auc"]

def score(actual, predicted, probability):
    return {
        "accuracy": accuracy_score(actual, predicted),
        "recall": recall_score(actual, predicted, zero_division=0),
        "auc": roc_auc_score(actual, probability),
    }

#run the FCA scorer and return each patient's disease score
def fca_scores_for(train_ctx, score_ctx):
    out = tempfile.NamedTemporaryFile(suffix=".csv", delete=False).name
    subprocess.run(["java", "-cp", jar, "Classify", train_ctx, score_ctx, "targets.txt",
                    str(min_support), str(min_confidence), out], check=True)
    prediction = pd.read_csv(out).set_index("patient")
    os.remove(out)
    return prediction["score"]

fca_scores = {m: [] for m in metrics}
ml_scores = {name: {m: [] for m in metrics} for name in models}
oof_rows = []

splitter = StratifiedKFold(n_splits=5, shuffle=True, random_state=0)
for train_index, test_index in splitter.split(x, y):
    x_train, x_test = x.iloc[train_index], x.iloc[test_index]
    y_train, y_test = y.iloc[train_index], y.iloc[test_index]

    #bins are fitted on the training rows only, then applied to both sides
    binariser = binarise.fit(method, x_train, y_train)
    train_bin = binarise.transform(binariser, x_train)
    test_bin = binarise.transform(binariser, x_test)

    #build the lattice on the training context, then score the test patients
    train_ctx = tempfile.NamedTemporaryFile(suffix=".csv", delete=False).name
    test_ctx = tempfile.NamedTemporaryFile(suffix=".csv", delete=False).name
    write_context(train_ctx, train_bin)
    write_context(test_ctx, test_bin)
    test_score = fca_scores_for(train_ctx, test_ctx).loc[x_test.index]
    os.remove(train_ctx)
    os.remove(test_ctx)

    #collect each test patient's out-of-fold score and label
    for patient in x_test.index:
        oof_rows.append({"patient": patient, "score": float(test_score.loc[patient]), "target": int(y_test.loc[patient])})

    #score the test fold at the default 0.50 cut
    for metric, value in score(y_test, (test_score >= 0.5).astype(int), test_score).items():
        fca_scores[metric].append(value)

    #ML baselines on the same fold-fitted binary features
    for name, model in models.items():
        model.fit(train_bin, y_train)
        predicted = model.predict(test_bin)
        probability = model.predict_proba(test_bin)[:, 1]
        for metric, value in score(y_test, predicted, probability).items():
            ml_scores[name][metric].append(value)

def summary(scores):
    return ", ".join(f"{m} {np.mean(scores[m]):.3f} +/- {np.std(scores[m]):.3f}" for m in metrics)

print(f"leakage-free {method} binarisation, 5-fold, FCA classifier vs ML baselines")
print(f"FCA classifier (threshold 0.50): {summary(fca_scores)}")
for name in models:
    print(f"{name}: {summary(ml_scores[name])}")

print("\naccuracy gap on each fold (FCA minus each model), mean over folds")
fca_accuracy = np.array(fca_scores["accuracy"])
for name in models:
    gap = float(np.mean(fca_accuracy - np.array(ml_scores[name]["accuracy"])))
    print(f"vs {name}: mean gap {gap:+.3f}")

#best non-baseline model by mean accuracy
best = max((name for name in models if name != "majority baseline"),
           key=lambda n: np.mean(ml_scores[n]["accuracy"]))
pd.DataFrame([{
    "method": method,
    "fca_accuracy": np.mean(fca_scores["accuracy"]),
    "fca_auc": np.mean(fca_scores["auc"]),
    "best_model": best,
    "best_model_accuracy": np.mean(ml_scores[best]["accuracy"]),
    "best_model_auc": np.mean(ml_scores[best]["auc"]),
}]).to_csv(f"summary_{method}.csv", index=False)

#out-of-fold score per test patient
oof = pd.DataFrame(oof_rows)
oof.to_csv(f"oof_scores_{method}.csv", index=False)
