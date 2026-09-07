import pandas as pd

columns = ["age", "sex", "cp", "trestbps", "chol", "fbs", "restecg", "thalach", "exang", "oldpeak", "slope", "ca", "thal", "num"]
data_path = "processed.cleveland.data"
heart = pd.read_csv(data_path, header=None, names=columns, na_values="?")

print("Total Rows:", len(heart))
print("Missing values per column:")
print(heart.isna().sum())
heart = heart.dropna().reset_index(drop=True)
print("Total Rows after dropping missing:", len(heart))

#add target column: 1 if num is above 0, else 0
heart["target"] = (heart["num"] > 0).astype(int)
features = heart.drop(columns=["num", "target"])
target = heart["target"]
print("\nclass balance (0 = no disease, 1 = disease):")
print(target.value_counts().sort_index())

#continuous and categorical feature lists
continuous = ["age", "trestbps", "chol", "thalach", "oldpeak"]
categorical = ["sex", "cp", "fbs", "restecg", "exang", "slope", "ca", "thal"]

print("\nContinuous features (ranges and quartiles):")
print(features[continuous].describe())

print("\nCategorical features (value counts):")
for col in categorical:
    print(f"\n{col}:")
    print(features[col].value_counts().sort_index())

heart[continuous + categorical + ["target"]].to_csv("heart_clean.csv", index=False)

#write patient id to label file
with open("targets.txt", "w") as f:
    for i, value in enumerate(target):
        f.write(f"p{i};{int(value)}\n")