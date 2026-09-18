// Represents a single income or expense record
public class Transaction {
    private int transactionId;
    private int userId;
    private TransactionType type;
    private String category;
    private double amount;
    private String date;
    private String description;

    // Constructor
    public Transaction(int transactionId, int userId, TransactionType type,
            String category, double amount, String date, String description) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    // Returns a negative amount if it's an expense, positive if income
    public double getSignedAmount() {
        if (this.type == TransactionType.EXPENSE) {
            return -this.amount;
        }
        return this.amount;
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public TransactionType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}