# Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis
Knowledge Inference and Data Mining with Formal Concept Analysis: Interpretable Heart Disease Diagnosis on the Cleveland Dataset

Gemini
New chat
Search chats
Images
Library
New notebook
Foundations of Markov Chain Monte Carlo and Bayesian Inference
Learning Without a Map: A Guide to Monte Carlo Methods
All notebooks
Adding a Google Docs Table of Contents
Academic Paper Acknowledgement Draft
Compact Microwave Size Assessment
Verified BibTeX References for Overleaf
Formatting Medical In-Text Citations
Harvard Citations for Health Websites
Report Introduction Revision
Academic Rewording for Medical Classifier
Understanding NAICS, SIC, NACE Classifications
TV Broadband With Streaming Subscriptions
LinkedIn Post Options For New Role
Utility Bill Cost Analysis
FCA Movie Recommendations and Classification
Student Accommodation Renting Experience
New Job Tax Code Explained
UK Spouse Visa Rules 2026-2027
What are the recent rules for spouse visa application and processing
Microsoft Certifications for Data Roles
Claude Desktop Screen Sharing Guide
SQL Views and Variables Explained
Change the background board behind the tv from the beige one to black textured wallpaper and the actual wallpaper to black paint.
Understanding Partially Observable Markov Decision Processes
Manchester to London Travel Cost
Power BI Nested Bookmark Navigation
DAX Measure for NPS Calculation
Question Answering Over Structured Data
Data Engineering Quiz Creation
Reinforcement Learning: Lectures 1-4

Activity
Old Trafford, Salford, UK
From your IP address
Update location
Conversation with Gemini
how to include a table of content in google docs

To create a table of contents in Google Docs, you first need to ensure your document is structured using Heading styles. Here is how to set it up:

1
Format your headings
The table uses these to build the structure
Highlight the text you want to appear in your table of contents. Click the styles dropdown in the toolbar (it usually says "Normal text") and select a heading level, such as Heading 1, Heading 2, or Heading 3.

2
Place your cursor
Click exactly where you want the table of contents to appear in your document (usually at the beginning).

3
Insert the table
In the top menu, click Insert, scroll down to the bottom of the menu, and select Table of contents.

4
Choose a style
Select the layout you prefer from the options:

Plain text: Text with page numbers on the right.

Dotted: Text with dotted lines leading to page numbers.

Links: Blue, clickable text that links directly to the section (best for digital viewing).

Want to know how to update the table later?

Yes
how can i push to github over here https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git from my local mac folder '/Users/nusaibazaman/Documents/Heart_Final FCA/github_upload'

To push your local project folder to GitHub, open Terminal on your Mac and follow these commands step-by-step.

Because your folder path contains spaces, make sure to wrap the path in quotation marks ("...") when navigating to it.

1
Open Terminal and navigate to your folder
Run the following command to move into your project directory:

Bash
cd "/Users/nusaibazaman/Documents/Heart_Final FCA/github_upload"
2
Initialize a local Git repository
Set up a new Git repository inside this folder:

Bash
git init
3
Stage and commit your files
Add all files in the directory to staging, then create your initial commit:

Bash
git add .
git commit -m "Initial commit"
4
Set your default branch name
Ensure your default branch is named main:

Bash
git branch -M main
5
Link your remote repository and push
Connect your local repository to GitHub and push your files:

Bash
git remote add origin https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git
git push -u origin main
Note: If prompted for authentication during the push, you may need to use a Personal Access Token (PAT) or log in via your GitHub account credentials / GitHub CLI if you haven't authenticated your terminal yet.

Want to know how to handle authentication if your push fails?

Yes
nothing was pushed, just a main branch was created

If the branch was created but no files are visible on GitHub, Git either didn't track the files, didn't commit them, or ignored them. Let's check what Git currently sees and push them again.

1
Check the repository status
Ensure you are still in your project folder, then run this command to see what Git sees:

Bash
git status
How to verify: The output will tell you if files are "untracked" (red), "Changes to be committed" (green), or if there is "nothing to commit".

2
Stage all files
If your files are listed as untracked, add all of them to the staging area:

Bash
git add .
How to verify: Run git status again. Your files should now be listed in green.

3
Commit the changes
Save the files to your local repository history:

Bash
git commit -m "Add project files"
How to verify: The terminal will output a list of the files that were just committed.

4
Push to GitHub
Push the new commit to your remote repository:

Bash
git push origin main
How to verify: The terminal will show a progress percentage and confirm the push to origin/main. Refresh your GitHub page to see the files.

If git status says "nothing to commit" but your files are definitely in that folder, they might be blocked by a .gitignore file, or there might be a hidden .git folder inside one of your project subfolders preventing them from being tracked.

Last login: Mon Sep  7 03:07:19 on ttys000

(base) nusaibazaman@Nusaibas-MacBook-Air ~ % cd "/Users/nusaibazaman/Documents/Heart_Final FCA/github_upload"

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git init

Initialized empty Git repository in /Users/nusaibazaman/Documents/Heart_Final FCA/github_upload/.git/

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git add .

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git commit -m "Initial commit"

[master (root-commit) def11f9] Initial commit

 13 files changed, 821 insertions(+)

 create mode 100644 .gitignore

 create mode 100644 binarise.py

 create mode 100644 build_contexts.py

 create mode 100644 clean_data.py

 create mode 100644 compare_models.py

 create mode 100644 context_example.csv

 create mode 100644 feature_risk.py

 create mode 100644 pom.xml

 create mode 100644 src/main/java/Classify.java

 create mode 100644 src/main/java/ExampleLattice.java

 create mode 100644 src/main/java/Lattice.java

 create mode 100644 summary_table.py

 create mode 100644 targets_example.txt

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git branch -M main

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git remote add origin https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git push -u origin main 

Username for 'https://github.com': Nusaiba Zaman

Password for 'https://Nusaiba Zaman@github.com': 

remote: Invalid username or token. Password authentication is not supported for Git operations.

fatal: Authentication failed for 'https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git/'

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git status

On branch main

nothing to commit, working tree clean

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git add .

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git commit -m "Add project files"

On branch main

nothing to commit, working tree clean

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git push origin main

Username for 'https://github.com': NusaibaZaman

Password for 'https://NusaibaZaman@github.com': 

remote: Invalid username or token. Password authentication is not supported for Git operations.

fatal: Authentication failed for 'https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git/'

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % 

GitHub removed support for regular account passwords in the terminal. To authenticate and push your code, you must generate a Personal Access Token (PAT) and use that as your password.

Your terminal log shows your files are already successfully tracked and committed locally. You just need to re-run the push command with a token.

1
Generate a Personal Access Token on GitHub
Log in to GitHub in your web browser.

Click your profile photo in the top right corner and select Settings.

Scroll down the left sidebar and click Developer settings (at the very bottom).

Click Personal access tokens, then select Tokens (classic).

Click Generate new token (classic).

2
Configure token permissions
This gives the token permission to upload code
Give the token a name (like "MacBook Terminal") and check the box next to repo (Full control of private repositories). Scroll to the bottom and click Generate token.

3
Copy the token
You cannot view this again after leaving the page
Copy the long string of characters that appears.

4
Run the push command again
Go back to your Mac terminal and run:

Bash
git push origin main
5
Enter your credentials
Your typing will be invisible when pasting the token
Username: Type exactly NusaibaZaman and press Return.

Password: Paste the token you just copied and press Return. (Note: The terminal will not show any characters or stars as you paste, but it is registering your input).

Want to save this token so you don't have to paste it every time?

Yes
(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % git push origin main

Username for 'https://github.com': NusaibaZaman

Password for 'https://NusaibaZaman@github.com': 

To https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git

 ! [rejected]        main -> main (fetch first)

error: failed to push some refs to 'https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git'

hint: Updates were rejected because the remote contains work that you do

hint: not have locally. This is usually caused by another repository pushing

hint: to the same ref. You may want to first integrate the remote changes

hint: (e.g., 'git pull ...') before pushing again.

hint: See the 'Note about fast-forwards' in 'git push --help' for details.

(base) nusaibazaman@Nusaibas-MacBook-Air github_upload % 

Your authentication worked perfectly! This new error (fetch first) means your online GitHub repository already contains some files (likely a README or LICENSE file you added when creating the repo online) that do not exist in your local Mac folder yet.

Git wants you to merge the online files with your local files before it allows you to push.

1
Pull the remote files to your Mac
Run this command to download the GitHub files and merge them with your local project. The special flag at the end is required because these two folders were started independently:

Bash
git pull origin main --allow-unrelated-histories
2
Save the merge message (if prompted)
If your terminal suddenly changes to a text editor
Git may automatically open a terminal text editor (like Vim) asking you to confirm the merge message. If your terminal fills with text and you can't type normally:

Type exactly :wq (which stands for write and quit).

Press Return.
(If the text editor doesn't open, just proceed to the next step).

3
Push everything back up to GitHub
Now that your local folder contains both sets of files in sync, you can push everything successfully:

Bash
git push origin main
write this as a read me file, 10712705

07/09/2026





Knowledge Inference and Data Mining with Formal Concept Analysis: Interpretable Heart Disease Diagnosis on the Cleveland Dataset



Additional Materials

MSc in Data Science (Computer Science Data Informatics)

DATA72000













Table of Contents:



1. Code Repository: 2

2. Files inside Repository 2

3. Software Environment: 3

4. Installing required Softwares: 3

4.1 macOS 4

4.2 Linux 4

4.3 Windows 4

5. Obtaining FCA4J library 4

6. Accessing the Dataset: 5

7. Running the project: 5

8. Output Files produced: 6

This document supports the Extended Research Project Report in reproducing the project results. It includes exact instructions, explains the files required and produced, lists the software and command prompts for ease of execution.







1. Code Repository:

All source code is uploaded in this public github repository:

Link:https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git





2. Files inside Repository

These files are required for reproducibility.

File

Role

clean_data.py

Reads processed.cleveland.data, removes incomplete records, writes heart_clean.csv and targets.txt

binarise.py

Binarisation module holding the clinical and entropy cut points, imported by build_contexts.py and compare_models.py

build_contexts.py

Reads heart_clean.csv and writes the clinical and entropy formal contexts

feature_risk.py

Reads a context and targets.txt, computes each attribute's odds ratio and Fisher's exact test and draws the risk figure

compare_models.py

Runs the five-fold comparison of the FCA classifier against the baselines and writes the summary and per-patient score files

summary_table.py

Reads the results and summary files and writes the combined summary table

pom.xml

Maven build file, declares the fca4j dependency and the main class

src/main/java/Lattice.java

Builds a lattice from a context and mines and prunes the diagnostic rules

src/main/java/Classify.java

Scores patients from a training context,  called by compare_models.py inside each fold

src/main/java/ExampleLattice.java

Builds the small worked-example lattice used for the methodology figure

context_example.csv

The small worked-example formal context (input for ExampleLattice)

targets_example.txt

The labels for the worked example

Table 1: Lists the repository files and explains what each file does



3. Software Environment:

The project was built and run on macOS with the versions below.

Component

Version

Role in the project

Python

3.11.14

Runs the cleaning, binarisation, risk, comparison and summary scripts

pandas

2.2.2

Reads and reshapes the tabular data

NumPy

1.26.4

Numerical arrays used across the Python scripts

scikit-learn

1.8.0

Baseline classifiers and the cross-validation split

SciPy

1.17.1

Fisher's exact test for the attribute statistics

Matplotlib

3.10.6

Draws the attribute risk figures

Java (OpenJDK)

21.0.6

Runs the lattice construction and rule mining

Apache Maven

3.9.16

Builds the Java code into a runnable jar

fca4j

0.4.7

Concept lattice construction and rule mining (LIRMM)

Graphviz (dot)

12.2.1

Renders the worked-example lattice to an image

Table 2: Software Versions used



The fca4j library is not on Maven Central and this project's pom.xml does not declare a repository for it, thus it is not downloaded automatically when the Java code is built. It must be placed in the local Maven repository first, which Section 5 explains.



4. Installing required Softwares:

The project requires five tools: a Java Development Kit (version 17 or newer), Apache Maven, Python 3, Graphviz and Git.



4.1 macOS

Step 1: Install Homebrew

/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"



Step 2: Install Java, Maven, Python, Graphviz and Git

brew install openjdk@21 maven python@3.11 graphviz git



Step 3: Install the Python libraries

pip3 install pandas numpy scikit-learn scipy matplotlib



4.2 Linux

sudo apt update

sudo apt install default-jdk maven python3 python3-pip graphviz git

pip3 install pandas numpy scikit-learn scipy matplotlib

4.3 Windows

winget install Microsoft.OpenJDK.21

winget install Apache.Maven

winget install Python.Python.3.11

winget install Graphviz.Graphviz

winget install Git.Git



python -m pip install pandas numpy scikit-learn scipy matplotlib



5. Obtaining FCA4J library

The Java build depends on fr.lirmm.fca4j:fca4j-io version 0.4.7. This is a research library from LIRMM, not a package on Maven Central, it has to be built once and installed into the local Maven repository before the project will build.

Clone the LIRMM fca4j project and install it. This places fca4j-io 0.4.7, along with the other fca4j modules it needs, into the local Maven repository.

git clone https://gite.lirmm.fr/gutierre/fca4j-project.git

cd fca4j-project

mvn clean install

cd ..

Version note: the project's pom.xml asks for 0.4.7. If the LIRMM project's current version is different, check the 0.4.7 release before installing, or change the version number in this project's pom.xml to match the version currently.





6. Accessing the Dataset:

The project uses the Cleveland subset of the UCI Heart Disease dataset. It has been downloaded from:



Link: https://archive.ics.uci.edu/dataset/45/heart+disease

After downloading the dataset file, select the processed.cleveland.data file and place it in the project root. The clean_data.py file reads the dataset directly, it drops the records with missing values and writes the cleaned dataset that the later steps use.





7. Running the project:

After cloning the github repository, run these steps exactly to reproduce the results:



Step 1: Install all python packages

pip install pandas numpy scikit-learn scipy matplotlib



Step 2: Build the Java tools

mvn clean package



Step 3: Clean the raw dataset

python3 clean_data.py



Step 4: Build the Formal Contexts (both Clinical and Entropy contexts)

python3 build_contexts.py



Step 5: Build the Lattice and mine the rules

java -cp target/<jar-name>.jar Lattice context_clinical.csv 30 0.70 targets.txt > results_clinical.txt



java -cp target/<jar-name>.jar Lattice context_entropy.csv 30 0.70 targets.txt > results_entropy.txt



Step 6: Compute the attribute risk statistics

python3 feature_risk.py context_clinical.csv



python3 feature_risk.py context_entropy.csv



Step 7: Compare FCA Classifier with Machine Learning models

python3 compare_models.py clinical



python3 compare_models.py entropy



Step 8: Build Combines summary table

python3 summary_table.py

 

Step 9: Render the worked example lattice

java -cp target/<jar-name>.jar ExampleLattice context_example.csv example_lattice.dot



dot -Tpng example_lattice.dot -o figure_3_2_lattice_example.png



8. Output Files produced:

These files will be produced as outputs after Section 7.

Output file

Produced by

Contains

heart_clean.csv

clean_data.py

The cleaned 297-patient dataset (13 features and the target)

targets.txt

clean_data.py

Each patient id with its 0 or 1 label, used by Lattice and feature_risk.py

context_clinical.csv

build_contexts.py

The clinical binarised formal context

context_entropy.csv

build_contexts.py

The entropy binarised formal context

results_clinical.txt

Lattice (clinical)

Lattice size and the mined and pruned diagnostic rules (clinical0

results_entropy.txt

Lattice (entropy)

Lattice size and the mined and pruned diagnostic rules (entropy)

feature_risk_output_clinical.csv

feature_risk.py

Per-attribute odds ratio, p-value and patient counts (clinical)

feature_risk_output_entropy.csv

feature_risk.py

Per-attribute odds ratio, p-value and patient counts (entropy)

figure_feature_risk_clinical.png

feature_risk.py

Odds-ratio bar chart, clinical (Figure 4a in the report)

figure_feature_risk_entropy.png

feature_risk.py

Odds-ratio bar chart, entropy (Figure 4b in the report)

summary_clinical.csv

compare_models.py

FCA versus best baseline accuracy and AUC (clinical)

summary_entropy.csv

compare_models.py

FCA versus best baseline accuracy and AUC (entropy)

oof_scores_clinical.csv

compare_models.py

The out-of-fold disease score for each patient (clinical)

oof_scores_entropy.csv

compare_models.py

The out-of-fold disease score for each patient (entropy)

summary_table.csv

summary_table.py

Combined lattice size, rule count and predictive metrics

example_lattice.dot

ExampleLattice

Graphviz description of the worked-example lattice

figure_3_2_lattice_example.png

dot (Graphviz)

The rendered worked-example lattice (Figure 2 in the report)

fca-skeleton-1.0-jar-with-dependencies.jar

mvn clean package

The runnable Java tool, with fca4j bundled inside

Step 2: Install Java, Maven, Python, Graphviz and Git

Bash
brew install openjdk@21 maven python@3.11 graphviz git
Step 3: Install the Python libraries

Bash
pip3 install pandas numpy scikit-learn scipy matplotlib
4.2 Linux
Bash
sudo apt update
sudo apt install default-jdk maven python3 python3-pip graphviz git
pip3 install pandas numpy scikit-learn scipy matplotlib
4.3 Windows
PowerShell
winget install Microsoft.OpenJDK.21
winget install Apache.Maven
winget install Python.Python.3.11
winget install Graphviz.Graphviz
winget install Git.Git

python -m pip install pandas numpy scikit-learn scipy matplotlib
5. Obtaining FCA4J library
The Java build depends on fr.lirmm.fca4j:fca4j-io version 0.4.7. This is a research library from LIRMM, not a package on Maven Central, it has to be built once and installed into the local Maven repository before the project will build.

Clone the LIRMM fca4j project and install it. This places fca4j-io 0.4.7, along with the other fca4j modules it needs, into the local Maven repository.

Bash
git clone [https://gite.lirmm.fr/gutierre/fca4j-project.git](https://gite.lirmm.fr/gutierre/fca4j-project.git)
cd fca4j-project
mvn clean install
cd ..
Version note: the project's pom.xml asks for 0.4.7. If the LIRMM project's current version is different, check the 0.4.7 release before installing, or change the version number in this project's pom.xml to match the version currently.

6. Accessing the Dataset
The project uses the Cleveland subset of the UCI Heart Disease dataset. It has been downloaded from:

Link: https://archive.ics.uci.edu/dataset/45/heart+disease

After downloading the dataset file, select the processed.cleveland.data file and place it in the project root. The clean_data.py file reads the dataset directly, it drops the records with missing values and writes the cleaned dataset that the later steps use.

7. Running the project
After cloning the github repository, run these steps exactly to reproduce the results:

Step 1: Install all python packages

Bash
pip install pandas numpy scikit-learn scipy matplotlib
Step 2: Build the Java tools

Bash
mvn clean package
Step 3: Clean the raw dataset

Bash
python3 clean_data.py
Step 4: Build the Formal Contexts (both Clinical and Entropy contexts)

Bash
python3 build_contexts.py
Step 5: Build the Lattice and mine the rules

Bash
java -cp target/<jar-name>.jar Lattice context_clinical.csv 30 0.70 targets.txt > results_clinical.txt
java -cp target/<jar-name>.jar Lattice context_entropy.csv 30 0.70 targets.txt > results_entropy.txt
Step 6: Compute the attribute risk statistics

Bash
python3 feature_risk.py context_clinical.csv
python3 feature_risk.py context_entropy.csv
Step 7: Compare FCA Classifier with Machine Learning models

Bash
python3 compare_models.py clinical
python3 compare_models.py entropy
Step 8: Build Combined summary table

Bash
python3 summary_table.py
Step 9: Render the worked example lattice

Bash
java -cp target/<jar-name>.jar ExampleLattice context_example.csv example_lattice.dot
dot -Tpng example_lattice.dot -o figure_3_2_lattice_example.png
8. Output Files produced
These files will be produced as outputs after Section 7.

Output file	Produced by	Contains
heart_clean.csv	clean_data.py	The cleaned 297-patient dataset (13 features and the target)
targets.txt	clean_data.py	Each patient id with its 0 or 1 label, used by Lattice and feature_risk.py
context_clinical.csv	build_contexts.py	The clinical binarised formal context
context_entropy.csv	build_contexts.py	The entropy binarised formal context
results_clinical.txt	Lattice (clinical)	Lattice size and the mined and pruned diagnostic rules (clinical)
results_entropy.txt	Lattice (entropy)	Lattice size and the mined and pruned diagnostic rules (entropy)
feature_risk_output_clinical.csv	feature_risk.py	Per-attribute odds ratio, p-value and patient counts (clinical)
feature_risk_output_entropy.csv	feature_risk.py	Per-attribute odds ratio, p-value and patient counts (entropy)
figure_feature_risk_clinical.png	feature_risk.py	Odds-ratio bar chart, clinical (Figure 4a in the report)
figure_feature_risk_entropy.png	feature_risk.py	Odds-ratio bar chart, entropy (Figure 4b in the report)
summary_clinical.csv	compare_models.py	FCA versus best baseline accuracy and AUC (clinical)
summary_entropy.csv	compare_models.py	FCA versus best baseline accuracy and AUC (entropy)
oof_scores_clinical.csv	compare_models.py	The out-of-fold disease score for each patient (clinical)
oof_scores_entropy.csv	compare_models.py	The out-of-fold disease score for each patient (entropy)
summary_table.csv	summary_table.py	Combined lattice size, rule count and predictive metrics
example_lattice.dot	ExampleLattice	Graphviz description of the worked-example lattice
figure_3_2_lattice_example.png	dot (Graphviz)	The rendered worked-example lattice (Figure 2 in the report)
fca-skeleton-1.0-jar-with-dependencies.jar	mvn clean package	The runnable Java tool, with fca4j bundled inside
"""		
with open("README.md", "w") as f:
f.write(content)
print("File generated")


```text?code_stdout&code_event_index=1
File generated

Your README Markdown file is ready!

MD icon
README
 MD 
I have formatted the document using Markdown standard practices, including appropriately leveled headings, numbered sections, formatted code blocks for all terminal commands, and easily readable tables for your files, software environment, and output files.

You can download this .md file and drag it straight into your GitHub repository or place it in your local folder (as README.md) before running git add README.md, git commit -m "Add README", and git push origin main.




Gemini is AI and can make mistakes, including about people. Your privacy & GeminiOpens in a new window

Analyzing
# Knowledge Inference and Data Mining with Formal Concept Analysis
## Interpretable Heart Disease Diagnosis on the Cleveland Dataset

**Student ID:** 10712705  
**Date:** 07/09/2026  
**Program:** MSc in Data Science (Computer Science Data Informatics)  
**Course:** DATA72000  

This document supports the Extended Research Project Report in reproducing the project results. It includes exact instructions, explains the files required and produced, lists the software and command prompts for ease of execution.

## Table of Contents
1. [Code Repository](#1-code-repository)
2. [Files inside Repository](#2-files-inside-repository)
3. [Software Environment](#3-software-environment)
4. [Installing required Softwares](#4-installing-required-softwares)
5. [Obtaining FCA4J library](#5-obtaining-fca4j-library)
6. [Accessing the Dataset](#6-accessing-the-dataset)
7. [Running the project](#7-running-the-project)
8. [Output Files produced](#8-output-files-produced)

---

## 1. Code Repository
All source code is uploaded in this public github repository:  
**Link:** [https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git](https://github.com/NusaibaZaman/Knowledge-Inference-and-Data-Mining-with-Formal-Concept-Analysis.git)

## 2. Files inside Repository
These files are required for reproducibility.

| File | Role |
| :--- | :--- |
| `clean_data.py` | Reads processed.cleveland.data, removes incomplete records, writes heart_clean.csv and targets.txt |
| `binarise.py` | Binarisation module holding the clinical and entropy cut points, imported by build_contexts.py and compare_models.py |
| `build_contexts.py` | Reads heart_clean.csv and writes the clinical and entropy formal contexts |
| `feature_risk.py` | Reads a context and targets.txt, computes each attribute's odds ratio and Fisher's exact test and draws the risk figure |
| `compare_models.py` | Runs the five-fold comparison of the FCA classifier against the baselines and writes the summary and per-patient score files |
| `summary_table.py` | Reads the results and summary files and writes the combined summary table |
| `pom.xml` | Maven build file, declares the fca4j dependency and the main class |
| `src/main/java/Lattice.java` | Builds a lattice from a context and mines and prunes the diagnostic rules |
| `src/main/java/Classify.java` | Scores patients from a training context, called by compare_models.py inside each fold |
| `src/main/java/ExampleLattice.java` | Builds the small worked-example lattice used for the methodology figure |
| `context_example.csv` | The small worked-example formal context (input for ExampleLattice) |
| `targets_example.txt` | The labels for the worked example |

*Table 1: Lists the repository files and explains what each file does*

## 3. Software Environment
The project was built and run on macOS with the versions below.

| Component | Version | Role in the project |
| :--- | :--- | :--- |
| Python | 3.11.14 | Runs the cleaning, binarisation, risk, comparison and summary scripts |
| pandas | 2.2.2 | Reads and reshapes the tabular data |
| NumPy | 1.26.4 | Numerical arrays used across the Python scripts |
| scikit-learn | 1.8.0 | Baseline classifiers and the cross-validation split |
| SciPy | 1.17.1 | Fisher's exact test for the attribute statistics |
| Matplotlib | 3.10.6 | Draws the attribute risk figures |
| Java (OpenJDK) | 21.0.6 | Runs the lattice construction and rule mining |
| Apache Maven | 3.9.16 | Builds the Java code into a runnable jar |
| fca4j | 0.4.7 | Concept lattice construction and rule mining (LIRMM) |
| Graphviz (dot) | 12.2.1 | Renders the worked-example lattice to an image |

*Table 2: Software Versions used*

The `fca4j` library is not on Maven Central and this project's `pom.xml` does not declare a repository for it, thus it is not downloaded automatically when the Java code is built. It must be placed in the local Maven repository first, which Section 5 explains.

## 4. Installing required Softwares
The project requires five tools: a Java Development Kit (version 17 or newer), Apache Maven, Python 3, Graphviz and Git.

### 4.1 macOS
**Step 1: Install Homebrew**
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

**Step 2: Install Java, Maven, Python, Graphviz and Git**
```bash
brew install openjdk@21 maven python@3.11 graphviz git
```

**Step 3: Install the Python libraries**
```bash
pip3 install pandas numpy scikit-learn scipy matplotlib
```

### 4.2 Linux
```bash
sudo apt update
sudo apt install default-jdk maven python3 python3-pip graphviz git
pip3 install pandas numpy scikit-learn scipy matplotlib
```

### 4.3 Windows
```powershell
winget install Microsoft.OpenJDK.21
winget install Apache.Maven
winget install Python.Python.3.11
winget install Graphviz.Graphviz
winget install Git.Git

python -m pip install pandas numpy scikit-learn scipy matplotlib
```

## 5. Obtaining FCA4J library
The Java build depends on `fr.lirmm.fca4j:fca4j-io` version `0.4.7`. This is a research library from LIRMM, not a package on Maven Central, it has to be built once and installed into the local Maven repository before the project will build.

Clone the LIRMM fca4j project and install it. This places fca4j-io 0.4.7, along with the other fca4j modules it needs, into the local Maven repository.
```bash
git clone https://gite.lirmm.fr/gutierre/fca4j-project.git
cd fca4j-project
mvn clean install
cd ..
```
*Version note: the project's pom.xml asks for 0.4.7. If the LIRMM project's current version is different, check the 0.4.7 release before installing, or change the version number in this project's pom.xml to match the version currently.*

## 6. Accessing the Dataset
The project uses the Cleveland subset of the UCI Heart Disease dataset. It has been downloaded from:  
**Link:** [https://archive.ics.uci.edu/dataset/45/heart+disease](https://archive.ics.uci.edu/dataset/45/heart+disease)

After downloading the dataset file, select the `processed.cleveland.data` file and place it in the project root. The `clean_data.py` file reads the dataset directly, it drops the records with missing values and writes the cleaned dataset that the later steps use.

## 7. Running the project
After cloning the github repository, run these steps exactly to reproduce the results:

**Step 1: Install all python packages**
```bash
pip install pandas numpy scikit-learn scipy matplotlib
```

**Step 2: Build the Java tools**
```bash
mvn clean package
```

**Step 3: Clean the raw dataset**
```bash
python3 clean_data.py
```

**Step 4: Build the Formal Contexts (both Clinical and Entropy contexts)**
```bash
python3 build_contexts.py
```

**Step 5: Build the Lattice and mine the rules**
```bash
java -cp target/<jar-name>.jar Lattice context_clinical.csv 30 0.70 targets.txt > results_clinical.txt
java -cp target/<jar-name>.jar Lattice context_entropy.csv 30 0.70 targets.txt > results_entropy.txt
```

**Step 6: Compute the attribute risk statistics**
```bash
python3 feature_risk.py context_clinical.csv
python3 feature_risk.py context_entropy.csv
```

**Step 7: Compare FCA Classifier with Machine Learning models**
```bash
python3 compare_models.py clinical
python3 compare_models.py entropy
```

**Step 8: Build Combined summary table**
```bash
python3 summary_table.py
```
    
**Step 9: Render the worked example lattice**
```bash
java -cp target/<jar-name>.jar ExampleLattice context_example.csv example_lattice.dot
dot -Tpng example_lattice.dot -o figure_3_2_lattice_example.png
```

## 8. Output Files produced
These files will be produced as outputs after Section 7.

| Output file | Produced by | Contains |
| :--- | :--- | :--- |
| `heart_clean.csv` | clean_data.py | The cleaned 297-patient dataset (13 features and the target) |
| `targets.txt` | clean_data.py | Each patient id with its 0 or 1 label, used by Lattice and feature_risk.py |
| `context_clinical.csv` | build_contexts.py | The clinical binarised formal context |
| `context_entropy.csv` | build_contexts.py | The entropy binarised formal context |
| `results_clinical.txt` | Lattice (clinical) | Lattice size and the mined and pruned diagnostic rules (clinical) |
| `results_entropy.txt` | Lattice (entropy) | Lattice size and the mined and pruned diagnostic rules (entropy) |
| `feature_risk_output_clinical.csv` | feature_risk.py | Per-attribute odds ratio, p-value and patient counts (clinical) |
| `feature_risk_output_entropy.csv` | feature_risk.py | Per-attribute odds ratio, p-value and patient counts (entropy) |
| `figure_feature_risk_clinical.png` | feature_risk.py | Odds-ratio bar chart, clinical (Figure 4a in the report) |
| `figure_feature_risk_entropy.png` | feature_risk.py | Odds-ratio bar chart, entropy (Figure 4b in the report) |
| `summary_clinical.csv` | compare_models.py | FCA versus best baseline accuracy and AUC (clinical) |
| `summary_entropy.csv` | compare_models.py | FCA versus best baseline accuracy and AUC (entropy) |
| `oof_scores_clinical.csv` | compare_models.py | The out-of-fold disease score for each patient (clinical) |
| `oof_scores_entropy.csv` | compare_models.py | The out-of-fold disease score for each patient (entropy) |
| `summary_table.csv` | summary_table.py | Combined lattice size, rule count and predictive metrics |
| `example_lattice.dot` | ExampleLattice | Graphviz description of the worked-example lattice |
| `figure_3_2_lattice_example.png` | dot (Graphviz) | The rendered worked-example lattice (Figure 2 in the report) |
| `fca-skeleton-1.0-jar-with-dependencies.jar` | mvn clean package | The runnable Java tool, with fca4j bundled inside |
README.md
Displaying README.md.
