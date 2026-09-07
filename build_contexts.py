import pandas as pd
import binarise

#build the full-data binary contexts
heart = pd.read_csv("heart_clean.csv")
y = heart["target"]
x = heart.drop(columns=["target"])
patient_ids = ["p" + str(i) for i in range(len(x))]

def write_context(context, path):
    with open(path, "w") as f:
        f.write(";" + ";".join(context.columns) + "\n")
        for patient, row in zip(patient_ids, context.itertuples(index=False)):
            cells = ["X" if value == 1 else "" for value in row]
            f.write(patient + ";" + ";".join(cells) + "\n")

for method in ["clinical", "entropy"]:
    binariser = binarise.fit(method, x, y)
    context = binarise.transform(binariser, x)
    write_context(context, f"context_{method}.csv")
    print(f"context_{method}.csv: {context.shape[0]} patients x {context.shape[1]} attributes")
