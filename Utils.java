import java.util.List;
import java.util.Scanner;

// Helper class for formatting text and getting user input
public class Utils {

    // Formats a number to Indian Rupees (e.g., Rs. 50.00)
    public static String formatCurrency(double amount) {
        if (amount < 0) {
            return String.format("-Rs. %.2f", Math.abs(amount));
        }
        return String.format("Rs. %.2f", amount);
    }

    // Prompts the user until they enter a non-empty string
    public static String promptNonEmpty(Scanner scanner, String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    // Displays a menu for categories so the user doesn't have to guess
    public static String promptCategory(Scanner scanner) {
        System.out.println("\nSelect a Category:");
        System.out.println("1. Food & Dining");
        System.out.println("2. Rent & Bills");
        System.out.println("3. Salary & Income");
        System.out.println("4. Shopping");
        System.out.println("5. Transport");
        System.out.println("6. Other (Type your own)");
        System.out.print("> ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                return "Food & Dining";
            case "2":
                return "Rent & Bills";
            case "3":
                return "Salary & Income";
            case "4":
                return "Shopping";
            case "5":
                return "Transport";
            case "6":
                System.out.print("Enter your custom category: ");
                return scanner.nextLine().trim();
            default:
                // If they typed a word instead of a number, just accept the word
                return choice.isEmpty() ? "General" : choice;
        }
    }

    // Prints a clean ASCII table for the terminal
    public static void printTable(String[] headers, List<String[]> rows) {
        if (rows.isEmpty()) {
            System.out.println("(No records to display.)");
            return;
        }

        // Print table header
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-12s | %-10s | %-20s | %-12s | %-20s\n",
                headers[0], headers[1], headers[2], headers[3], headers[4], headers[5]);
        System.out.println(
                "---------------------------------------------------------------------------------------------");

        // Print each row
        for (String[] row : rows) {
            System.out.printf("%-5s | %-12s | %-10s | %-20s | %-12s | %-20s\n",
                    row[0], row[1], row[2], row[3], row[4], row[5]);
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------");
    }
}