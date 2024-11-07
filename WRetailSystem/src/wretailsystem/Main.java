package wretailsystem;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private CategoryUI categoryUI; 
    private ClothingUI clothingUI; 

    public Main() {
        //setting up the main GUI components
        setTitle("Women's Retail Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        JLabel titleLabel = new JLabel("Welcome to Women's Retail Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180)); 
        //Creates buttons for the main screen
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10)); 
        JButton clothingItemsButton = createStyledButton("Clothing Items");
        JButton discountManagementButton = createStyledButton("Discount Management");
        JButton categoryButton = createStyledButton("Category");
        JButton exitButton = createStyledButton("Exit"); // Exit button
        JButton searchButton = createStyledButton("Search");
        // Add buttons to the panel
        buttonPanel.add(clothingItemsButton);
        buttonPanel.add(discountManagementButton);
        buttonPanel.add(categoryButton);
        buttonPanel.add(exitButton); 
        buttonPanel.add(searchButton);
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        //Action listener that goes to CategoryMangementGUI
        categoryButton.addActionListener(e -> {
            CategoryManagementGUI categoryGUI = new CategoryManagementGUI(categoryUI);
            categoryGUI.setVisible(true);
        });

        //Action listener that opens ClothingManagementGUI
        clothingItemsButton.addActionListener(e -> {
            ClothingManagementGUI clothingGUI = new ClothingManagementGUI(clothingUI, categoryUI); // Pass both clothingUI and categoryUI
            clothingGUI.setVisible(true);
        });

        //Action listener that goes to DiscountManagementGUI
        discountManagementButton.addActionListener(e -> {
            DiscountManagementGUI discountGUI = new DiscountManagementGUI(clothingUI, categoryUI);
            discountGUI.setVisible(true);
        });

        //Action listener to exit the program
        exitButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", 
                                                              "Exit", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                System.exit(0);//closes the app
            }
        });
        searchButton.addActionListener(e -> {
    SearchGUI searchGUI = new SearchGUI(clothingUI, categoryUI);
    searchGUI.setVisible(true);
});

        setVisible(true);
    }

    //function to create each button and its apparance which returns it.
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(60, 179, 113)); 
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(46, 139, 87)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) 
        ));
        return button;
    }
    

    //Initialize database tables on startup
    private static void initializeDatabase() {
        ClothingDAO clothingDAO = new ClothingDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        clothingDAO.createClothingTable();
        categoryDAO.createCategoryTable();
    }

    public static void main(String[] args) {
        Password password = new Password("users.txt");

        //Authentication loop
        while (!password.authenticate()) {
            JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", 
                                          "Authentication Failed", JOptionPane.ERROR_MESSAGE);
        }

        SwingUtilities.invokeLater(() -> {
            initializeDatabase();

            //Set up Category-related components
            CategoryDAO categoryDAO = new CategoryDAO();
            CategoryService categoryService = new CategoryService(categoryDAO);
            CategoryUI categoryUI = new CategoryUI(categoryService);

            //Set up Clothing-related components
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