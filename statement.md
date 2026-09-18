# Problem Statement

## Problem Statement
Modern personal finance applications are frequently bloated with unnecessary graphical interfaces, intrusive advertisements, and cloud-syncing requirements that compromise data privacy. Users who prefer keyboard-driven workflows or operate in resource-constrained environments lack a lightweight, offline, and purely functional tool to track their finances securely. 

## Scope of the Project
The project is a terminal-based Personal Finance Engine developed in Java. It operates entirely via the Command Line Interface (CLI) and utilizes a local SQLite database for persistent storage. The scope encompasses:
* Local user authentication and secure password hashing.
* CRUD (Create, Read, Update, Delete) operations for daily income and expenses.
* Automated financial analytics (balance calculation, categorical breakdown, and budget monitoring).
* Strict data isolation ensuring multiple users can utilize the same system without accessing each other's financial records.

## Target Users
* Software developers and IT professionals who prefer CLI tools.
* Students requiring a fast, offline, and lightweight application to track daily expenses.
* Privacy-conscious individuals who do not want their financial data stored on third-party cloud servers.

## High-Level Features
1. **User Identity Module:** Secure registration and login using SHA-256 cryptographic hashing.
2. **Transaction Manager:** Interactive prompts to log income/expenses with categorized tagging and timestamping.
3. **Analytics Engine:** Automated calculation of net balance and categorical spending aggregates.
4. **Budget Watchdog:** Real-time budget limit verification with threshold warnings.
