package wretailsystem;
/*
This class createas the GUI for applying discounts to items by
category. Users can select a category to apply a discount to.
*/

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DiscountManagementGUI extends JFrame {

    private CategoryUI categoryUI;
    private ClothingUI clothingUI;

    public DiscountManagementGUI(ClothingUI clothingUI, CategoryUI categoryUI) {
        this.clothingUI = clothingUI;
        this.categoryUI = categoryUI;

        setTitle("Apply Discount by Category");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 1, 10, 10)); 

       
        JPanel discountPanel = new JPanel(new FlowLayout());
        JLabel discountLabel = new JLabel("Enter Discount Percentage:");
        JTextField discountField = new JTextField(10);
        discountPanel.add(discountLabel);
        discountPanel.add(discountField);

        
        JPanel categoryPanel = new JPanel(new FlowLayout());
        JLabel categoryLabel = new JLabel("Select Category:");
        JComboBox<String> categoryDropdown = new JComboBox<>();
        List<Category> categories = categoryUI.getAllCategories();
        for (Category category : categories) {
            categoryDropdown.addItem(category.getCategoryID() + " - " + category.getCategoryName());
        }
        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryDropdown);

        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton applyButton = new JButton("Apply Discount");
        JButton backButton = new JButton("Back"); 
        buttonPanel.add(applyButton);
        buttonPanel.add(backButton); 

        add(discountPanel);
        add(categoryPanel);
        add(buttonPanel);

        applyButton.addActionListener(e -> {
            try {
                double discountPercentage = Double.parseDouble(discountField.getText());
                if (discountPercentage < 0 || discountPercentage > 100) {
                    JOptionPane.showMessageDialog(this, "Enter a valid percentage (0-100).", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String selectedCategory = (String) categoryDropdown.getSelectedItem();
                String categoryID = selectedCategory.split(" - ")[0];

                clothingUI.applyDiscountByCategory(categoryID, discountPercentage);
                JOptionPane.showMessageDialog(this, "Discount applied successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid discount percentage.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        backButton.addActionListener(e -> dispose()); 
    }
}
