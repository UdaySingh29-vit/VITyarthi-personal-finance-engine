import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Base64;

// Handles user registration, login, and password security
public class Auth {

    // Scrambles the password so it is never saved as plain text
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Checks rules and registers a new user in the database
    public static User registerUser(String username, String password) throws Exception {
        if (username == null || username.trim().isEmpty()) {
            throw new Exception("Username cannot be empty.");
        }
        if (password == null || password.length() < 6) {
            throw new Exception("Password must be at least 6 characters.");
        }

        String hashedPass = hashPassword(password);
        String createdAt = LocalDateTime.now().toString(); // Gets the exact current date/time

        // user_id is 0 here because the database will automatically assign the real ID
        User newUser = new User(0, username.trim(), hashedPass, createdAt);

        return Storage.createUser(newUser);
    }

    // Checks if the typed password matches the saved scrambled password
    public static User loginUser(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("Invalid username or password.");
        }

        User user = Storage.getUserByUsername(username.trim());

        // If the user doesn't exist, we throw a generic error
        if (user == null) {
            throw new Exception("Invalid username or password.");
        }

        // Scramble the typed password and compare it to the saved one
        String hashedInput = hashPassword(password);
        if (!hashedInput.equals(user.getPasswordHash())) {
            throw new Exception("Invalid username or password.");
        }

        return user; // Login successful!
    }
}
