package wretailsystem;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private CategoryUI categoryUI; // Reference to CategoryUI

    public Main() {
        setTitle("Welcome to Women's Retail Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton clothingItemsButton = new JButton("Clothing Items");
        JButton managePriceButton = new JButton("Manage Price");
        JButton categoryButton = new JButton("Category");

        panel.add(clothingItemsButton);
        panel.add(managePriceButton);
        panel.add(categoryButton);

        add(panel, BorderLayout.CENTER);

        categoryButton.addActionListener(e -> {
            CategoryManagementGUI categoryGUI = new CategoryManagementGUI(categoryUI);
            categoryGUI.setVisible(true);
        });

        setVisible(true);
    }

    private static void initializeDatabase() {
        ClothingDAO clothingDAO = new ClothingDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        clothingDAO.createClothingTable();
        categoryDAO.createCategoryTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            initializeDatabase();

            CategoryDAO categoryDAO = new CategoryDAO();
            CategoryService categoryService = new CategoryService(categoryDAO);
            CategoryUI categoryUI = new CategoryUI(categoryService);

            Main mainWindow = new Main();
            mainWindow.categoryUI = categoryUI;
            mainWindow.setVisible(true);
        });
    }
}
