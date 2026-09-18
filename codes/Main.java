import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// The main entry point of the program
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        // Step 1: Set up the database file
        Storage.initializeDatabase();
        System.out.println("=== Welcome to the Personal Finance Engine ===");

        // Step 2: Show the login/register menu
        while (true) {
            if (currentUser == null) {
                authMenu();
            } else {
                userDashboard();
            }
        }
    }

    // --- Authentication Menu ---
    private static void authMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine().trim();

        try {
            if (choice.equals("1")) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                currentUser = Auth.loginUser(username, password);
                System.out.println("Login successful! Welcome, " + currentUser.getUsername() + ".");

            } else if (choice.equals("2")) {
                System.out.print("Choose a username: ");
                String username = scanner.nextLine();
                System.out.print("Choose a password (min 6 chars): ");
                String password = scanner.nextLine();
                Auth.registerUser(username, password);
                System.out.println("Registration successful! You can now log in.");

            } else if (choice.equals("3")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Main User Dashboard ---
    private static void userDashboard() {
        System.out.println("\n=== Dashboard (" + currentUser.getUsername() + ") ===");
        System.out.println("1. Add Transaction");
        System.out.println("2. View Transactions");
        System.out.println("3. View Total Balance");
        System.out.println("4. View Expense Breakdown");
        System.out.println("5. Check Monthly Budget");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                addTransactionMenu();
                break;
            case "2":
                viewTransactions();
                break;
            case "3":
                viewBalance();
                break;
            case "4":
                viewBreakdown();
                break;
            case "5":
                checkBudgetMenu();
                break;
            case "6":
                currentUser = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // --- Feature Menus ---

    private static void addTransactionMenu() {
        try {
            System.out.println("\nType: 1 for Income, 2 for Expense");
            System.out.print("> ");
            String typeChoice = scanner.nextLine().trim();
            TransactionType type = typeChoice.equals("1") ? TransactionType.INCOME : TransactionType.EXPENSE;

            String category = Utils.promptCategory(scanner);

            System.out.print("Enter Amount (e.g. 500.50): ");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter Description (optional): ");
            String description = scanner.nextLine().trim();

            String date = LocalDate.now().toString(); // Uses today's date automatically YYYY-MM-DD

            // Create and save
            Transaction txn = new Transaction(0, currentUser.getUserId(), type, category, amount, date, description);
            Storage.addTransaction(txn);
            System.out.println("Transaction saved successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for the amount.");
        }
    }

    private static void viewTransactions() {
        List<Transaction> txns = Storage.getTransactions(currentUser.getUserId());
        String[] headers = { "ID", "Date", "Type", "Category", "Amount", "Description" };
        List<String[]> rows = new ArrayList<>();

        for (Transaction t : txns) {
            String[] row = {
                    String.valueOf(t.getTransactionId()),
                    t.getDate(),
                    t.getType().name(),
                    t.getCategory(),
                    Utils.formatCurrency(t.getAmount()),
                    t.getDescription()
            };
            rows.add(row);
        }

        System.out.println("\n--- Your Transactions ---");
        Utils.printTable(headers, rows);
    }

    private static void viewBalance() {
        double balance = Analytics.calculateBalance(currentUser.getUserId());
        System.out.println("\nCurrent Total Balance: " + Utils.formatCurrency(balance));
    }

    private static void viewBreakdown() {
        Map<String, Double> breakdown = Analytics.categoryBreakdown(currentUser.getUserId());
        System.out.println("\n--- Expense Breakdown ---");
        if (breakdown.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        for (Map.Entry<String, Double> entry : breakdown.entrySet()) {
            System.out.println(entry.getKey() + ": " + Utils.formatCurrency(entry.getValue()));
        }
    }

    private static void checkBudgetMenu() {
        try {
            System.out.print("\nEnter your budget limit for this month: ");
            double limit = Double.parseDouble(scanner.nextLine().trim());
            Analytics.checkBudget(currentUser.getUserId(), limit);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }
    }
}
