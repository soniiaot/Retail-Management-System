package wretailsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryDAO {

    private static final String DB_URL = "jdbc:derby:rms;create=true";

    // Establish a database connection
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Create the Category table if it doesn't exist
    public void createCategoryTable() {
        String createTableSQL = "CREATE TABLE Category (" +
                "id VARCHAR(50) PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL" +
                ")";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Category table created or already exists.");
        } catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) { // Ignore "table already exists" error
                e.printStackTrace();
            }
        }
    }

    // Add a new category to the Category table
    public void addCategory(Category category) {
        String insertSQL = "INSERT INTO Category (id, name) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, category.getCategoryID());
            pstmt.setString(2, category.getCategoryName());
            pstmt.executeUpdate();
            System.out.println("Category added to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Additional methods for update, delete, and retrieve can be added as needed
}

