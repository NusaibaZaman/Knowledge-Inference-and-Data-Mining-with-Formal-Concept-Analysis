import sys
import pandas as pd
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt
from scipy.stats import fisher_exact
from matplotlib.patches import Patch

context_path = sys.argv[1] if len(sys.argv) > 1 else "context_clinical.csv"

context = pd.read_csv(context_path, sep=";", index_col=0)
x = (context == "X").astype(int)
labels = pd.read_csv("targets.txt", sep=";", header=None, index_col=0)[1]
y = labels.loc[x.index]

rows = []
for attr in x.columns:
    present = x[attr] == 1
    diseased_present = int((present & (y == 1)).sum())
    healthy_present = int((present & (y == 0)).sum())
    diseased_absent = int((~present & (y == 1)).sum())
    healthy_absent = int((~present & (y == 0)).sum())
    #Haldane correction keeps the odds ratio finite; Fisher's exact test gives a valid p-value at any cell count
    odds_ratio = ((diseased_present + 0.5) * (healthy_absent + 0.5)) / \
                 ((healthy_present + 0.5) * (diseased_absent + 0.5))
    _, p_value = fisher_exact([[diseased_present, healthy_present],
                               [diseased_absent, healthy_absent]])
    rows.append((attr, diseased_present + healthy_present, odds_ratio, p_value))

table = pd.DataFrame(rows, columns=["attribute", "patients_with_attribute", "odds_ratio", "p_value"])
table = table.sort_values("odds_ratio", ascending=False)

name = context_path.replace("context_", "").replace(".csv", "")
table.to_csv(f"feature_risk_output_{name}.csv", index=False)

pd.set_option("display.float_format", lambda v: f"{v:.3f}")
print(f"Attribute association with disease on {context_path} (odds ratio > 1 favours disease, sorted strongest first)")
print(table.to_string(index=False))

#bar chart of the attributes furthest from an odds ratio of 1
table["distance"] = table["odds_ratio"].apply(lambda v: v if v >= 1 else 1 / v)
top = table.sort_values("distance", ascending=False).head(12).sort_values("odds_ratio")

#light purple bars lean towards disease, light blue bars are protective
disease_colour, protective_colour = "#b39ddb", "#90caf9"
colours = [disease_colour if v >= 1 else protective_colour for v in top["odds_ratio"]]

plt.figure(figsize=(8, 6))
bars = plt.barh(top["attribute"], top["odds_ratio"], color=colours)
plt.axvline(1, color="black", linewidth=1)
plt.xscale("log")
plt.xlabel("Odds Ratio for disease (log scale)")
plt.title(f"Attribute association with disease ({name} binarisation)")
#print the exact odds ratio at the end of each bar so the reader can read the value directly
plt.gca().bar_label(bars, labels=[f"{v:.2f}" for v in top["odds_ratio"]], padding=3, fontsize=8)
xmin, xmax = plt.xlim()
plt.xlim(xmin, xmax * 1.8)
#legend mapping the two bar colours to their meaning
plt.legend(handles=[Patch(color=disease_colour, label="leans towards disease"),
                    Patch(color=protective_colour, label="protective")], loc="lower right")
plt.tight_layout()
plt.savefig(f"figure_feature_risk_{name}.png", dpi=150)
