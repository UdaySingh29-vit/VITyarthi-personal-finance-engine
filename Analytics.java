import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Handles math operations like calculating balances and grouping expenses
public class Analytics {

    // Calculates the total balance (Total Income - Total Expenses)
    public static double calculateBalance(int userId) {
        List<Transaction> transactions = Storage.getTransactions(userId);
        double balance = 0.0;

        for (Transaction t : transactions) {
            // getSignedAmount() automatically adds if Income, subtracts if Expense
            balance += t.getSignedAmount();
        }

        return balance;
    }

    // Adds up all expenses and groups them by category
    public static Map<String, Double> categoryBreakdown(int userId) {
        List<Transaction> transactions = Storage.getTransactions(userId);
        Map<String, Double> totals = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE) {
                String category = t.getCategory();
                // Get the current total for this category (or 0 if it doesn't exist yet)
                double currentTotal = totals.getOrDefault(category, 0.0);
                totals.put(category, currentTotal + t.getAmount());
            }
        }

        return totals;
    }

    // Checks if total expenses have gone over the user's budget limit
    public static void checkBudget(int userId, double budgetLimit) {
        List<Transaction> transactions = Storage.getTransactions(userId);
        double totalSpent = 0.0;

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE) {
                totalSpent += t.getAmount();
            }
        }

        System.out.println("\n--- Budget Check ---");
        System.out.println("Budget Limit: " + Utils.formatCurrency(budgetLimit));
        System.out.println("Total Spent:  " + Utils.formatCurrency(totalSpent));

        double remaining = budgetLimit - totalSpent;
        System.out.println("Remaining:    " + Utils.formatCurrency(remaining));

        if (totalSpent > budgetLimit) {
            double overage = totalSpent - budgetLimit;
            System.out.println("⚠ WARNING: You are over budget by " + Utils.formatCurrency(overage) + "!");
        } else {
            System.out.println("Great job! You are within your budget.");
        }
    }
}