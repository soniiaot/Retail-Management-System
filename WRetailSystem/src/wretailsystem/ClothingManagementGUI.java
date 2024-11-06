package wretailsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClothingManagementGUI extends JFrame {

    private JTable clothingTable;
    private DefaultTableModel tableModel;
    private ClothingUI clothingUI;
    private CategoryUI categoryUI;  // Reference to CategoryUI for fetching categories

    public ClothingManagementGUI(ClothingUI clothingUI, CategoryUI categoryUI) {
        this.clothingUI = clothingUI;
        this.categoryUI = categoryUI;
        setTitle("Clothing Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadClothingData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Size", "Color", "Price", "Brand", "Quantity", "Category ID"}, 0);
        clothingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clothingTable);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0)); // Adjusted for 4 buttons
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back"); // Back button

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton); // Add Back button

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> showAddClothingDialog());
        updateButton.addActionListener(e -> showUpdateClothingDialog());
        deleteButton.addActionListener(e -> showDeleteClothingDialog());

        // Action listener for the Back button to close the current window
        backButton.addActionListener(e -> dispose()); // Close the current window (back to previous page)
    }

    private void loadClothingData() {
        tableModel.setRowCount(0);
        List<ClothingItem> clothingItems = clothingUI.getAllClothing();
        for (ClothingItem item : clothingItems) {
            tableModel.addRow(new Object[]{item.getId(), item.getSize(), item.getColor(), item.getPrice(), item.getBrand(), item.getQuantity(), item.getCategoryID()});
        }
    }

    private void showAddClothingDialog() {
        String size = JOptionPane.showInputDialog(this, "Enter Size:");
        String color = JOptionPane.showInputDialog(this, "Enter Color:");
        double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Price:"));
        String brand = JOptionPane.showInputDialog(this, "Enter Brand:");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Quantity:"));

        List<Category> categories = categoryUI.getAllCategories();
        JComboBox<String> categoryDropdown = new JComboBox<>();
        for (Category category : categories) {
            categoryDropdown.addItem(category.getCategoryID() + " - " + category.getCategoryName());
        }

        int result = JOptionPane.showConfirmDialog(this, categoryDropdown, "Select Category", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String selectedCategory = (String) categoryDropdown.getSelectedItem();
            String categoryID = selectedCategory.split(" - ")[0];

            clothingUI.createClothing(size, color, price, brand, quantity, categoryID);
            loadClothingData();
        }
    }

    private void showUpdateClothingDialog() {
        int selectedRow = clothingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a clothing item to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String clothingID = (String) tableModel.getValueAt(selectedRow, 0);
        String size = (String) tableModel.getValueAt(selectedRow, 1);
        String color = (String) tableModel.getValueAt(selectedRow, 2);
        double price = Double.parseDouble(tableModel.getValueAt(selectedRow, 3).toString());
        String brand = (String) tableModel.getValueAt(selectedRow, 4);
        int quantity = Integer.parseInt(tableModel.getValueAt(selectedRow, 5).toString());

        size = JOptionPane.showInputDialog(this, "Enter New Size:", size);
        color = JOptionPane.showInputDialog(this, "Enter New Color:", color);
        price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter New Price:", price));
        brand = JOptionPane.showInputDialog(this, "Enter New Brand:", brand);
        quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter New Quantity:", quantity));

        List<Category> categories = categoryUI.getAllCategories();
        JComboBox<String> categoryDropdown = new JComboBox<>();
        for (Category category : categories) {
            categoryDropdown.addItem(category.getCategoryID() + " - " + category.getCategoryName());
        }

        // Set selected item in the dropdown based on existing categoryID
        String currentCategoryID = clothingUI.getClothingByID(clothingID).getCategoryID();
        for (int i = 0; i < categoryDropdown.getItemCount(); i++) {
            if (categoryDropdown.getItemAt(i).startsWith(currentCategoryID + " -")) {
                categoryDropdown.setSelectedIndex(i);
                break;
            }
        }

        int result = JOptionPane.showConfirmDialog(this, categoryDropdown, "Select Category", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String selectedCategory = (String) categoryDropdown.getSelectedItem();
            String categoryID = selectedCategory.split(" - ")[0];

            clothingUI.updateClothing(clothingID, size, color, price, brand, quantity, categoryID);
            loadClothingData();
        }
    }

    private void showDeleteClothingDialog() {
        int selectedRow = clothingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a clothing item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String clothingID = (String) tableModel.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this clothing item?", "Delete Clothing", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            clothingUI.deleteClothing(clothingID);
            loadClothingData();
        }
    }
}