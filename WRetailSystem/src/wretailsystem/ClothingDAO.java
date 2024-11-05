package wretailsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ClothingDAO {

    static final String CLOTHING_DB_URL = "jdbc:derby:C:/COMP603/Retail-Management-System/WRetailSystem/ClothingItemDB;create=true";

    // Establish a database connection
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(CLOTHING_DB_URL);
    }

    // Create the Clothing table if it doesn't exist
    public void createClothingTable() {
        String createTableSQL = "CREATE TABLE Clothing (" +
                "id VARCHAR(50) PRIMARY KEY, " +
                "size VARCHAR(20), " +
                "color VARCHAR(20), " +
                "price DOUBLE, " +
                "brand VARCHAR(50), " +
                "quantity INT" +
                ")";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Clothing table created or already exists.");
        } catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) { // Ignore "table already exists" error
                e.printStackTrace();
            }
        }
    }

    // Add a new clothing item to the Clothing table
    public void addClothing(ClothingItem clothing) {
        String insertSQL = "INSERT INTO Clothing (id, size, color, price, brand, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, clothing.getId());
            pstmt.setString(2, clothing.getSize());
            pstmt.setString(3, clothing.getColor());
            pstmt.setDouble(4, clothing.getPrice());
            pstmt.setString(5, clothing.getBrand());
            pstmt.setInt(6, clothing.getQuantity());
            pstmt.executeUpdate();
            System.out.println("Clothing item added to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Additional methods for update, delete, and retrieve can be added as needed
}



