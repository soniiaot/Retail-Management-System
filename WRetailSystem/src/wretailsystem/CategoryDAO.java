package wretailsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    static final String CATEGORY_DB_URL = "jdbc:derby:C:/COMP603/Retail-Management-System/WRetailSystem/CategoryDB;create=true";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(CATEGORY_DB_URL);
    }

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
            if (!"X0Y32".equals(e.getSQLState())) {
                e.printStackTrace();
            }
        }
    }

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

    public void updateCategory(Category category) {
        String updateSQL = "UPDATE Category SET name = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, category.getCategoryName());
            pstmt.setString(2, category.getCategoryID());
            pstmt.executeUpdate();
            System.out.println("Category updated in database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(String categoryID) {
        String deleteSQL = "DELETE FROM Category WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, categoryID);
            pstmt.executeUpdate();
            System.out.println("Category deleted from database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT id, name FROM Category";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
