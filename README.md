# Terminal-Based Personal Finance Engine

## Overview
A lightweight, secure, and entirely CLI-based Personal Finance Engine built in Java. This application allows users to manage their daily expenses, track income, and generate financial reports directly from the terminal without the overhead of a graphical user interface (GUI) or the privacy concerns of cloud-based applications.

## Features
* **Secure Authentication:** Multi-user support with SHA-256 password hashing and isolated local sessions.
* **Interactive Dashboard:** Menu-driven terminal interface for seamless navigation.
* **Transaction Management:** Add, view, and organize income and expenses by category.
* **Financial Analytics:** Instantly view total net balance and categorical expense breakdowns.
* **Budget Tracking:** Set monthly spending limits and receive automated warnings when exceeding them.

## Technologies & Tools Used
* **Language:** Java (JDK 11 or higher)
* **Database:** SQLite (via `sqlite-jdbc-3.42.0.0.jar`)
* **Security:** `java.security.MessageDigest` for cryptographic hashing
* **Paradigm:** Object-Oriented Programming (OOP)

## Steps to Install & Run
1. **Clone the Repository:**
   ```bash
   git clone <your-repository-url>
   cd <your-repository-folder>