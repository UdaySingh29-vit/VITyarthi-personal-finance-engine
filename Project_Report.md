


# 1. Cover Page
**Project Title:** Terminal-Based Personal Finance Engine
**Course:** Programming in Java (CSE2006)
**Student Name:** Uday Pratap Singh
**Registration / Roll Number:** 25BAI11360

---

# 2. Introduction
The Terminal-Based Personal Finance Engine is a lightweight, offline Java application designed to help users track their income and expenses through a highly efficient Command Line Interface (CLI). By eliminating the graphical user interface, the application maximizes execution speed and minimizes system resource consumption, providing a secure, local environment for personal financial management.

# 3. Problem Statement
Modern personal finance applications are frequently bloated with unnecessary GUI elements and cloud-syncing requirements that compromise user privacy. Users operating in terminal environments lack a purely functional, keyboard-driven tool to track their finances securely. This project addresses the need for a localized, privacy-first financial ledger.

# 4. Functional Requirements
1. **User Management:** The system must allow users to register securely and authenticate via login.
2. **Transaction Logging (CRUD):** The system must allow users to input income and expenses, categorizing them via an interactive menu.
3. **Data Retrieval:** The system must display a user's transaction history in a formatted, readable ASCII table.
4. **Analytics:** The system must calculate the total net balance dynamically.
5. **Budget Monitoring:** The system must allow the user to input a budget limit and warn them if categorical expenses exceed this threshold.

# 5. Non-Functional Requirements
1. **Security:** Passwords must be hashed using SHA-256; plain-text passwords must never be stored.
2. **Data Isolation:** A logged-in user must only have access to their own transaction records.
3. **Reliability:** Data must persist across sessions via a local SQLite database, preventing data loss upon terminal exit.
4. **Maintainability:** The codebase must adhere to Object-Oriented Programming (OOP) principles, separating database operations from business logic and CLI routing.

# 6. System Architecture
The application follows a 3-tier architecture:
* **Presentation Layer (CLI):** Managed by `Main.java` and `Utils.java`, handling user input and terminal output formatting.
* **Business Logic Layer:** Managed by `Auth.java` and `Analytics.java`, processing hashing, math calculations, and object instantiation (`User.java`, `Transaction.java`).
* **Persistence Layer:** Managed by `Storage.java`, handling the SQLite JDBC connection and execution of SQL queries.

# 7. Design Diagrams

**A. Use Case Diagram (Textual Representation)**
* **Actor:** User
* **Use Cases:** Register Account, Login, Add Transaction, View Ledger, Check Balance, View Expense Breakdown, Check Budget.

**B. Workflow Diagram**
`Start -> Auth Menu (Login/Register) -> Authenticate DB -> Main Dashboard -> Select Option (1-6) -> Execute Logic (Analytics/Storage) -> Return to Dashboard -> Logout -> Exit`

**C. Class Diagram (Key Entities)**
* `User`: (userId, username, passwordHash, createdAt)
* `Transaction`: (transactionId, userId, type, category, amount, date, description)
* `Storage`: Executes `initializeDatabase()`, `createUser()`, `addTransaction()`
* `Analytics`: Executes `calculateBalance()`, `categoryBreakdown()`

**D. Entity-Relationship (ER) Diagram**
* **USERS Table:** `user_id` (PK), `username`, `password_hash`, `created_at`
* **TRANSACTIONS Table:** `transaction_id` (PK), `user_id` (FK -> USERS), `type`, `category`, `amount`, `date`, `description`
* *Relationship:* One-to-Many (One User has Many Transactions).

# 8. Design Decisions & Rationale
* **Java:** Chosen for robust Object-Oriented capabilities and strong standard libraries.
* **SQLite:** Chosen over MySQL/PostgreSQL as it requires no background server process, keeping the application portable and locally isolated.
* **CLI over GUI:** Ensures strict adherence to the project guidelines preventing GUI-based setups, while maximizing performance and scriptability.

# 9. Implementation Details
The project was modularized into specific classes to enforce separation of concerns:
* `Models (User.java, Transaction.java, TransactionType.java)`: Pure data containers.
* `Storage.java`: The sole class permitted to execute SQL statements.
* `Auth.java`: Encapsulates `java.security.MessageDigest` for SHA-256 operations.
* `Utils.java`: Centralizes input validation and currency formatting (`Rs.`).
* `Main.java`: Contains the `Scanner` loops and terminal menus.

# 10. Screenshots / Results
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
<br>

# 11. Testing Approach
Testing was conducted manually via terminal execution flows:
* **Input Validation:** Passed empty strings to `Utils.promptNonEmpty()` to ensure the loop rejects invalid data.
* **Security Testing:** Attempted login with an incorrect password to verify denial of access.
* **Integration Testing:** Added an expense of `Rs. 500` and verified that the `calculateBalance()` method instantly reflected a `-Rs. 500` shift in the database.

# 12. Challenges Faced
* **JDBC Driver Integration:** Configuring the Java classpath to recognize the external `sqlite-jdbc.jar` dependency resulted in `No suitable driver found` errors initially. This was resolved by properly mapping the JAR file in the IDE's Referenced Libraries and modifying the execution command.
* **Terminal Formatting:** Aligning multi-column data in the terminal required dynamic string formatting (`System.out.printf`) to maintain a clean ASCII table structure.

# 13. Learnings & Key Takeaways
* Mastered the integration of SQLite within a Java environment using JDBC.
* Gained practical experience with cryptographic hashing (SHA-256) for secure credential storage.
* Reinforced core OOP concepts by preventing the CLI layer from interacting directly with the database layer.

# 14. Future Enhancements
* **Data Export:** Implement a feature to export the transaction history to a `.csv` file.
* **Recurring Transactions:** Add support for automated monthly subscriptions (e.g., Netflix, Rent).
* **Multi-Currency Support:** Allow users to log transactions in USD/EUR and automatically convert them to INR (Rs.) based on stored rates.

# 15. References
* Oracle Java Documentation: https://docs.oracle.com/en/java/
* SQLite Documentation: https://www.sqlite.org/docs.html
* SQLite JDBC GitHub Repository: https://github.com/xerial/sqlite-jdbc
