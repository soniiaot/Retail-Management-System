package wretailsystem;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private CategoryUI categoryUI; // Reference to CategoryUI
    private ClothingUI clothingUI; // Reference to ClothingUI

    public Main() {
        setTitle("Women's Retail Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        JLabel titleLabel = new JLabel("Welcome to Women's Retail Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180)); // SteelBlue color

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // Adjusted to include 4 buttons

        JButton clothingItemsButton = createStyledButton("Clothing Items");
        JButton discountManagementButton = createStyledButton("Discount Management");
        JButton categoryButton = createStyledButton("Category");
        JButton exitButton = createStyledButton("Exit"); // Exit button

        // Add buttons to the panel
        buttonPanel.add(clothingItemsButton);
        buttonPanel.add(discountManagementButton);
        buttonPanel.add(categoryButton);
        buttonPanel.add(exitButton); // Add Exit button

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Action listener for Category button to open CategoryManagementGUI
        categoryButton.addActionListener(e -> {
            CategoryManagementGUI categoryGUI = new CategoryManagementGUI(categoryUI);
            categoryGUI.setVisible(true);
        });

        // Action listener for Clothing Items button to open ClothingManagementGUI
        clothingItemsButton.addActionListener(e -> {
            ClothingManagementGUI clothingGUI = new ClothingManagementGUI(clothingUI, categoryUI); // Pass both clothingUI and categoryUI
            clothingGUI.setVisible(true);
        });

        // Action listener for Discount Management button to open DiscountManagementGUI
        discountManagementButton.addActionListener(e -> {
            DiscountManagementGUI discountGUI = new DiscountManagementGUI(clothingUI, categoryUI);
            discountGUI.setVisible(true);
        });

        // Action listener for Exit button to exit the program
        exitButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", 
                                                              "Exit", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                System.exit(0); // Close the application
            }
        });

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(60, 179, 113)); // MediumSeaGreen color
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(46, 139, 87)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) 
        ));
        return button;
    }
    

    // Initialize database tables on startup
    private static void initializeDatabase() {
        ClothingDAO clothingDAO = new ClothingDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        clothingDAO.createClothingTable();
        categoryDAO.createCategoryTable();
    }

    public static void main(String[] args) {
        Password password = new Password("users.txt");

        // Authentication loop
        while (!password.authenticate()) {
            JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", 
                                          "Authentication Failed", JOptionPane.ERROR_MESSAGE);
        }

        SwingUtilities.invokeLater(() -> {
            initializeDatabase();

            // Set up Category-related components
            CategoryDAO categoryDAO = new CategoryDAO();
            CategoryService categoryService = new CategoryService(categoryDAO);
            CategoryUI categoryUI = new CategoryUI(categoryService);

            // Set up Clothing-related components
            ClothingDAO clothingDAO = new ClothingDAO();
            ClothingService clothingService = new ClothingService(clothingDAO);
            ClothingUI clothingUI = new ClothingUI(clothingService);

            // Create the main window and pass UI components
            Main mainWindow = new Main();
            mainWindow.categoryUI = categoryUI;
            mainWindow.clothingUI = clothingUI;
            mainWindow.setVisible(true);
        });
    }
}