import numpy as np
import pandas as pd
from sklearn.tree import DecisionTreeClassifier

#binarisation fitted on training rows, then applied to any rows
continuous = ["age", "trestbps", "chol", "thalach", "oldpeak"]
categorical = ["sex", "cp", "fbs", "restecg", "exang", "slope", "ca", "thal"]

clinical_edges = {
    "age":      [0, 45, 60, 200],
    "trestbps": [0, 120, 140, 400],
    "chol":     [0, 200, 240, 700],
    "thalach":  [0, 120, 150, 300],
    "oldpeak":  [-0.1, 1.0, 2.0, 10],
}
clinical_labels = {
    "age":      ["age_lt45", "age_45to59", "age_ge60"],
    "trestbps": ["bp_normal", "bp_elevated", "bp_high"],
    "chol":     ["chol_desirable", "chol_borderline", "chol_high"],
    "thalach":  ["hr_low", "hr_mid", "hr_high"],
    "oldpeak":  ["st_low", "st_mid", "st_high"],
}

#learn the categorical values and the continuous bin edges from the training rows
def fit(method, x, y):
    categories = {col: sorted(x[col].astype(int).unique()) for col in categorical}
    edges = {}
    labels = {}
    if method == "clinical":
        edges = clinical_edges
        labels = clinical_labels
    elif method == "entropy":
        for col in continuous:
            tree = DecisionTreeClassifier(criterion="entropy", max_leaf_nodes=4, random_state=0)
            tree.fit(x[[col]], y)
            thresholds = sorted(t for t in tree.tree_.threshold if t != -2)
            edges[col] = [-np.inf] + thresholds + [np.inf]
            labels[col] = [f"{col}_{i}" for i in range(len(edges[col]) - 1)]
    return {"categories": categories, "edges": edges, "labels": labels}

#apply a fitted binariser to produce the binary columns
def transform(binariser, x):
    parts = []
    for col in categorical:
        values = pd.Categorical(x[col].astype(int), categories=binariser["categories"][col])
        dummies = pd.get_dummies(values, prefix=col).astype(int)
        dummies.index = x.index
        parts.append(dummies)
    for col in continuous:
        codes = pd.cut(x[col], bins=binariser["edges"][col], labels=False, right=False)
        for i, name in enumerate(binariser["labels"][col]):
            parts.append(pd.Series((codes == i).astype(int), index=x.index, name=name))
    return pd.concat(parts, axis=1)
