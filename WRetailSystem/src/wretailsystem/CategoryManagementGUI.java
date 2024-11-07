package wretailsystem;
/*
This class deals with the interface aspect of the
category tab. This includes buttons for the add, 
update and delete features. 
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryManagementGUI extends JFrame {

    private JTable categoryTable;
    private DefaultTableModel tableModel;
    private CategoryUI categoryUI;

    public CategoryManagementGUI(CategoryUI categoryUI) {
        this.categoryUI = categoryUI;
        setTitle("Category Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadCategoryData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Category ID", "Category Name"}, 0);
        categoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoryTable);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0)); // Adjusted to fit Back button only

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> showAddCategoryDialog());
        updateButton.addActionListener(e -> showUpdateCategoryDialog());
        deleteButton.addActionListener(e -> showDeleteCategoryDialog());

        // Back button action to close the current window
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
            }
        });
    }

    private void loadCategoryData() {
        tableModel.setRowCount(0);
        List<Category> categories = categoryUI.getAllCategories();
        for (Category category : categories) {
            tableModel.addRow(new Object[]{category.getCategoryID(), category.getCategoryName()});
        }
    }

    private void showAddCategoryDialog() {
        String categoryName = JOptionPane.showInputDialog(this, "Enter Category Name:", "Create New Category", JOptionPane.PLAIN_MESSAGE);
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            categoryUI.createCategory(categoryName);
            loadCategoryData();
        }
    }

    private void showUpdateCategoryDialog() {
        int selectedRow = categoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a category to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String categoryID = (String) tableModel.getValueAt(selectedRow, 0);
        String currentName = (String) tableModel.getValueAt(selectedRow, 1);

        String newName = JOptionPane.showInputDialog(this, "Enter New Category Name:", currentName);
        if (newName != null && !newName.trim().isEmpty()) {
            categoryUI.updateCategory(categoryID, newName);
            loadCategoryData();
        }
    }

    private void showDeleteCategoryDialog() {
        int selectedRow = categoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a category to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String categoryID = (String) tableModel.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this category?", "Delete Category", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = categoryUI.deleteCategory(categoryID);
            if (success) {
                loadCategoryData();
            } else {
                JOptionPane.showMessageDialog(this, "Cannot delete category as it is linked to existing products.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}