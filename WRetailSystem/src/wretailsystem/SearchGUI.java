package wretailsystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchGUI extends JFrame {
    private ClothingUI clothingUI;
    private JComboBox<String> criteriaDropdown;
    private JTextField searchField;
    private JTextArea resultArea;

    public SearchGUI(ClothingUI clothingUI, CategoryUI categoryUI) {
        this.clothingUI = clothingUI;
        setTitle("Search Clothing Items");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Search criteria dropdown
        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel criteriaLabel = new JLabel("Search by:");
        criteriaDropdown = new JComboBox<>(new String[]{"ID", "Name", "Size", "Color", "Price", "Brand", "Category ID"});
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        searchPanel.add(criteriaLabel);
        searchPanel.add(criteriaDropdown);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose()); // Close the window

        // Add components to the frame
        add(searchPanel, BorderLayout.NORTH);
        add(resultScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> performSearch());
    }

    private void performSearch() {
        String criteria = (String) criteriaDropdown.getSelectedItem();
        String searchValue = searchField.getText().trim();

        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search value.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<ClothingItem> results;
        switch (criteria) {
            case "ID":
                results = clothingUI.searchById(searchValue);
                break;
            case "Name":
                results = clothingUI.searchByName(searchValue);
                break;
            case "Size":
                results = clothingUI.searchBySize(searchValue);
                break;
            case "Color":
                results = clothingUI.searchByColor(searchValue);
                break;
            case "Price":
                try {
                    double price = Double.parseDouble(searchValue);
                    results = clothingUI.searchByPrice(price);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "Brand":
                results = clothingUI.searchByBrand(searchValue);
                break;
            case "Category ID":
                results = clothingUI.searchByCategoryID(searchValue);
                break;
            default:
                results = null;
        }

        displayResults(results);
    }

    private void displayResults(List<ClothingItem> results) {
        resultArea.setText("");
        if (results == null || results.isEmpty()) {
            resultArea.append("No results found.");
            return;
        }
        for (ClothingItem item : results) {
            resultArea.append("ID: " + item.getId() + "\n");
            resultArea.append("Name: " + item.getName() + "\n");
            resultArea.append("Size: " + item.getSize() + "\n");
            resultArea.append("Color: " + item.getColor() + "\n");
            resultArea.append("Price: " + item.getPrice() + "\n");
            resultArea.append("Brand: " + item.getBrand() + "\n");
            resultArea.append("Quantity: " + item.getQuantity() + "\n");
            resultArea.append("Category ID: " + item.getCategoryID() + "\n");
            resultArea.append("-------------------------\n");
        }
    }
}
