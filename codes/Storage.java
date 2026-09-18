import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Handles all database connections and queries
public class Storage {
    // This will create a file named finance_engine.db in your project folder
    private static final String DB_URL = "jdbc:sqlite:finance_engine.db";

    // Creates the tables if they don't exist yet
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {

            // Create Users table
            String createUsers = "CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT NOT NULL UNIQUE, " +
                    "password_hash TEXT NOT NULL, " +
                    "created_at TEXT NOT NULL)";
            stmt.execute(createUsers);

            // Create Transactions table
            String createTransactions = "CREATE TABLE IF NOT EXISTS transactions (" +
                    "transaction_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "type TEXT NOT NULL, " +
                    "category TEXT NOT NULL, " +
                    "amount REAL NOT NULL, " +
                    "date TEXT NOT NULL, " +
                    "description TEXT, " +
                    "FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE)";
            stmt.execute(createTransactions);

        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }

    // --- User Methods ---

    // Saves a new user to the database
    public static User createUser(User user) throws Exception {
        String sql = "INSERT INTO users(username, password_hash, created_at) VALUES(?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getCreatedAt());
            pstmt.executeUpdate();

            // Get the ID that the database auto-generated
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUserId(rs.getInt(1));
                }
            }
            return user;
        } catch (SQLException e) {
            throw new Exception("Username might already be taken.");
        }
    }

    // Finds a user by their username (used for logging in)
    public static User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("created_at"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding user: " + e.getMessage());
        }
        return null; // Return null if user does not exist
    }

    // --- Transaction Methods ---

    // Saves a new transaction to the database
    public static Transaction addTransaction(Transaction txn) {
        String sql = "INSERT INTO transactions(user_id, type, category, amount, date, description) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, txn.getUserId());
            pstmt.setString(2, txn.getType().name()); // Saves "INCOME" or "EXPENSE"
            pstmt.setString(3, txn.getCategory());
            pstmt.setDouble(4, txn.getAmount());
            pstmt.setString(5, txn.getDate());
            pstmt.setString(6, txn.getDescription());
            pstmt.executeUpdate();

            // Get the ID that the database auto-generated
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    txn.setTransactionId(rs.getInt(1));
                }
            }
            return txn;
        } catch (SQLException e) {
            System.out.println("Error adding transaction: " + e.getMessage());
            return null;
        }
    }

    // Gets all transactions for a specific user, sorted by newest first
    public static List<Transaction> getTransactions(int userId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY date DESC, transaction_id DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Transaction t = new Transaction(
                            rs.getInt("transaction_id"),
                            rs.getInt("user_id"),
                            TransactionType.fromString(rs.getString("type")),
                            rs.getString("category"),
                            rs.getDouble("amount"),
                            rs.getString("date"),
                            rs.getString("description"));
                    list.add(t);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting transactions: " + e.getMessage());
        }
        return list;
    }

    // Deletes a transaction by ID (makes sure it belongs to the user first)
    public static boolean deleteTransaction(int transactionId, int userId) {
        String sql = "DELETE FROM transactions WHERE transaction_id = ? AND user_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            pstmt.setInt(2, userId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting transaction: " + e.getMessage());
            return false;
        }
    }
}
