import re
import pandas as pd

#combine the structural numbers and the predictive numbers into one table
methods = ["clinical", "entropy"]
rows = []
for method in methods:
    text = open(f"results_{method}.txt").read()
    lattice = re.search(r"full lattice: (\d+) concepts, (\d+) edges, height (\d+)", text)
    rule_count = re.search(r"total pruned rules: (\d+)", text)
    summary = pd.read_csv(f"summary_{method}.csv").iloc[0]
    rows.append({
        "method": method,
        "lattice_concepts": int(lattice.group(1)),
        "lattice_height": int(lattice.group(3)),
        "rules": int(rule_count.group(1)),
        "fca_accuracy": round(float(summary["fca_accuracy"]), 3),
        "fca_auc": round(float(summary["fca_auc"]), 3),
        "best_ml": summary["best_model"],
        "best_ml_accuracy": round(float(summary["best_model_accuracy"]), 3),
        "best_ml_auc": round(float(summary["best_model_auc"]), 3),
    })

table = pd.DataFrame(rows)
table.to_csv("summary_table.csv", index=False)
print(table.to_string(index=False))
