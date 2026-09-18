// Enum to restrict transactions to only Income or Expense
public enum TransactionType {
    INCOME, 
    EXPENSE;

    // Helper to convert string input to Enum
    public static TransactionType fromString(String type) {
        if (type.equalsIgnoreCase("income")) {
            return INCOME;
        } else if (type.equalsIgnoreCase("expense")) {
            return EXPENSE;
        }
        throw new IllegalArgumentException("Invalid type. Must be Income or Expense.");
    }
}
