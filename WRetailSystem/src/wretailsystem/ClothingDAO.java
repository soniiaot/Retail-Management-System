package wretailsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothingDAO {

static final String CLOTHING_DB_URL = "jdbc:derby:./WRetailSystem/ClothingItemDB;create=true";

    // Establish a database connection
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(CLOTHING_DB_URL);
    }
public void dropClothingTable() {
    String dropTableSQL = "DROP TABLE Clothing";
    try (Connection conn = connect();
         Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(dropTableSQL);
        System.out.println("Clothing table dropped.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void createClothingTable() {
    String createTableSQL = "CREATE TABLE Clothing (" +
            "id VARCHAR(50) PRIMARY KEY, " +
            "size VARCHAR(20), " +
            "color VARCHAR(20), " +
            "price DOUBLE, " +
            "brand VARCHAR(50), " +
            "quantity INT, " +
            "categoryID VARCHAR(50)" + // New column for category ID
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
    String insertSQL = "INSERT INTO Clothing (id, size, color, price, brand, quantity, categoryID) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
        pstmt.setString(1, clothing.getId());
        pstmt.setString(2, clothing.getSize());
        pstmt.setString(3, clothing.getColor());
        pstmt.setDouble(4, clothing.getPrice());
        pstmt.setString(5, clothing.getBrand());
        pstmt.setInt(6, clothing.getQuantity());
        pstmt.setString(7, clothing.getCategoryID());
        pstmt.executeUpdate();
        System.out.println("Clothing item added to database.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Update an existing clothing item in the Clothing table
    public void updateClothing(ClothingItem clothing) {
        String updateSQL = "UPDATE Clothing SET size = ?, color = ?, price = ?, brand = ?, quantity = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, clothing.getSize());
            pstmt.setString(2, clothing.getColor());
            pstmt.setDouble(3, clothing.getPrice());
            pstmt.setString(4, clothing.getBrand());
            pstmt.setInt(5, clothing.getQuantity());
            pstmt.setString(6, clothing.getId());
            pstmt.executeUpdate();
            System.out.println("Clothing item updated in database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a clothing item from the Clothing table
    public void deleteClothing(String clothingID) {
        String deleteSQL = "DELETE FROM Clothing WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, clothingID);
            pstmt.executeUpdate();
            System.out.println("Clothing item deleted from database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all clothing items from the database
public List<ClothingItem> getAllClothing() {
    List<ClothingItem> clothingItems = new ArrayList<>();
    String query = "SELECT id, size, color, price, brand, quantity, categoryID FROM Clothing";
    try (Connection conn = connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            String id = rs.getString("id");
            String size = rs.getString("size");
            String color = rs.getString("color");
            double price = rs.getDouble("price");
            String brand = rs.getString("brand");
            int quantity = rs.getInt("quantity");
            String categoryID = rs.getString("categoryID");

            ClothingItem clothingItem = new ClothingItem(id, size, color, price, brand, quantity, categoryID);
            clothingItems.add(clothingItem);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return clothingItems;
}
}
