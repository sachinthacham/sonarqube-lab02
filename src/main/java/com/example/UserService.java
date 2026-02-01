package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // SECURITY FIX: Never hardcode credentials. 
    // Best practice: Use Environment Variables or a secure Vault.
    private String getDbPassword() {
        String envPassword = System.getenv("DB_PASSWORD");
        if (envPassword == null) {
            // Fallback for local testing only - In production this should throw an error
            return "root"; 
        }
        return envPassword;
    }

    private Connection getConnection() throws SQLException {
        // Ideally, URL and User should also be in config files
        return DriverManager.getConnection("jdbc:mysql://localhost/db", "root", getDbPassword());
    }

    public void findUser(String username) {
        String query = "SELECT * FROM users WHERE name = ?"; // Use ? placeholder

        // Try-with-resources ensures the Connection and Statement close automatically
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username); // Safe binding
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    System.out.println("User found: " + rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error during findUser: " + e.getMessage());
        }
    }

    public void deleteUser(String username) {
        String query = "DELETE FROM users WHERE name = ?"; // Use ? placeholder

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username); // Safe binding
            int rowsAffected = pst.executeUpdate();
            System.out.println("Deleted " + rowsAffected + " user(s).");

        } catch (SQLException e) {
            System.err.println("Database error during deleteUser: " + e.getMessage());
        }
    }
}