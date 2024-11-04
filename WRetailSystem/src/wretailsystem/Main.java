package wretailsystem;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private CategoryUI categoryUI; // Reference to CategoryUI

    public Main() {
        // Set the title of the frame
        setTitle("Welcome to Women's Retail Management System");

        // Set the size of the window
        setSize(400, 300);

        // Specify what happens when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel with GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column, 10px gap

        // Create buttons for Clothing Items, Manage Price, and Category
        JButton clothingItemsButton = new JButton("Clothing Items");
        JButton managePriceButton = new JButton("Manage Price");
        JButton categoryButton = new JButton("Category");

        // Add buttons to the panel
        panel.add(clothingItemsButton);
        panel.add(managePriceButton);
        panel.add(categoryButton);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Action listener for Category button to open CategoryManagementGUI
        categoryButton.addActionListener(e -> {
            // Open the CategoryManagementGUI window
            CategoryManagementGUI categoryGUI = new CategoryManagementGUI(categoryUI);
            categoryGUI.setVisible(true);
        });

        // Set the frame to be visible
        setVisible(true);
    }

    // Initialize database and tables on startup
    private static void initializeDatabase() {
        ClothingDAO clothingDAO = new ClothingDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        
        // Create tables if they don't exist
        clothingDAO.createClothingTable();
        categoryDAO.createCategoryTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialize the database tables
            initializeDatabase();

            // Initialize services and UI components
            CategoryService categoryService = new CategoryService(); // Initialize with your data source
            CategoryUI categoryUI = new CategoryUI(categoryService); // Pass it to CategoryUI

            // Create the main window and set up dependencies
            Main mainWindow = new Main();
            mainWindow.categoryUI = categoryUI; // Pass the CategoryUI instance to the Main window
            mainWindow.setVisible(true);
        });
    }
}
