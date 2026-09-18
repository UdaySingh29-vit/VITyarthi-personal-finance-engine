Terminal-Based Personal Finance Engine
VITyarthi Programming in Java (CSE2006) Flipped course project, Java

# Introduction
To design a simple console-based Java program that helps end-users track their budget and expenses securely via an interactive terminal menu using a local database. <br>
**Name:** Uday Pratap Singh <br>
**Reg no.:** 25BAI11360

# Overview of the project
This is a command-line interface (CLI) application designed for personal monthly expense and budget tracking. The program is implemented in Java and utilizes a local SQLite database for persistent storage to store daily and cumulative financial data. The primary goal of the system is to help a user securely manage their account, monitor their spending across several categories, manage a monthly budget, and track cumulative expenses without the overhead of a graphical user interface (GUI). 

# Features
* **Secure Authentication:** Multi-user support with SHA-256 password hashing and isolated local sessions.

* **Transaction Management (CRUD):** Interactive prompts to log income and expenses with categorized tagging and timestamping.

* **Expense Categorization:** Supports logging expenses into specific categories:<br>
           - Food & Dining<br>
           - Rent & Bills<br>
           - Salary & Income<br>
           - Shopping<br>
           - Transport<br>
           - Other (Custom user-defined category)

* **Daily and Cumulative Totals:** Automatically tracks and calculates the total net balance based on logged income and expenses.

* **Historical Reporting:** Generates and displays a detailed, tabular ASCII chart showing the complete transaction history.

* **Real-Time Budget Tracking:** Allows the user to set a monthly spending limit and generates automated warnings when expenses exceed this threshold.

# Technologies/Tools Used
* **Language:** Java (JDK 11 or higher)
* **Database:** SQLite (via `sqlite-jdbc-3.42.0.0.jar`)
* **Security:** `java.security.MessageDigest` for cryptographic hashing
* **Code Editor:** VS Code

# Steps to Install & Run
1.  **Prerequisites:** Ensure you have Java installed on your system. You can check by running `java -version` in your terminal.<br>
2.  **Download:** Download all the `.java` files and the `sqlite-jdbc-3.42.0.0.jar` driver to a local directory.<br>
3.  **Compile:** Open your terminal or command prompt, navigate to the directory, and run:
    ```bash
    javac *.java
    ```
4.  **Run:** Execute the compiled program by including the SQLite driver in the classpath:
    * **On Windows:** `java -cp ".;sqlite-jdbc-3.42.0.0.jar" Main`
    * **On macOS/Linux:** `java -cp ".:sqlite-jdbc-3.42.0.0.jar" Main`

# Instructions For Testing 
1. Start the VS Code.
2. Open the terminal and run the program. Use the test cases given below to get the results:
* **User Registration:** Choose **option 2** from the initial menu to register a new user with a password (minimum 6 characters).
* **User Login:** Choose **option 1** to log in using your newly created credentials.
* **Add expenses and budget update:** You can add expenses by choosing **option 1** from the menu, later choose the sub category for adding expenses in to particular section where you money was spent.
* **To print expense chart till date:** By choosing **option 2** from the dashboard menu you can print the expense chart till the moment.
* **Check Balance & Breakdown:** Choose **option 3** to see the net balance, and **option 4** to see cumulative expenses grouped by category.
* **Budget check:** By choosing **option 5** from the menu you can input a monthly budget limit and test the over-budget warning system.
* **To exit the program:** By choosing **option 6** from the menu you can log out of the expense tracker safely.

# Screenshots

**1. Initial Welcome Menu**<br>
<img width="530" height="157" alt="Screenshot 2026-09-18 184926" src="https://github.com/user-attachments/assets/31b71781-f9b7-496f-a726-4e31edfebfda" />


**2. User Registration**<br>
<img width="510" height="176" alt="Screenshot 2026-09-18 184957" src="https://github.com/user-attachments/assets/6f3bad70-4900-4830-8a15-b66f04945344" />


**3. User Login & Dashboard Menu**<br>
<img width="330" height="385" alt="Screenshot 2026-09-18 185018" src="https://github.com/user-attachments/assets/8fa0aa19-c718-4df1-bdc7-c9f886db5cc6" />


**4. Adding an Income Transaction**<br>
<img width="537" height="525" alt="Screenshot 2026-09-18 185052" src="https://github.com/user-attachments/assets/b33faae5-72f1-4901-9975-b92028864ed4" />


**5. Adding an Expense Transaction**<br>
<img width="420" height="526" alt="Screenshot 2026-09-18 185120" src="https://github.com/user-attachments/assets/825b8762-8f45-4929-8b79-e2c31b3dd6e4" />


**6. Viewing the Transaction Table**<br>
<img width="876" height="390" alt="Screenshot 2026-09-18 185133" src="https://github.com/user-attachments/assets/9554d109-e78e-4b7b-832b-fa721f9a5ccc" />


**7. Budget Tracking & Warning Check**<br>
<img width="491" height="372" alt="Screenshot 2026-09-18 185155" src="https://github.com/user-attachments/assets/e279e099-1a84-4b47-8ed8-113d1e3e0646" />


**8. User Logout**<br>
<img width="430" height="208" alt="Screenshot 2026-09-18 185209" src="https://github.com/user-attachments/assets/98c38cd7-2dff-474e-9aaf-202590b187d4" />


**9. Exiting the Application**<br>
<img width="382" height="125" alt="Screenshot 2026-09-18 185222" src="https://github.com/user-attachments/assets/7620b37d-68b5-4bdd-87b1-202317fb9382" />
