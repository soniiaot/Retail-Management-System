/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wretailsystem;

/**
 *
 * @author fancy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetailManagementGUI extends JFrame {

    // Constructor to set up the GUI components
    public RetailManagementGUI() {
        // Set the title of the frame
        setTitle("Retail Management System");
        
        // Set the size of the window
        setSize(600, 400);
        
        // Specify what happens when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the main panel where all components will go
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Using a grid layout for simplicity
        
        // Create some basic labels and input fields
        JLabel clothingLabel = new JLabel("Enter Clothing Item:");
        JTextField clothingField = new JTextField(20);
        
        JLabel priceLabel = new JLabel("Enter Price:");
        JTextField priceField = new JTextField(10);
        
        JButton addButton = new JButton("Add Item");
        JButton viewButton = new JButton("View Items");
        
        // Add components to the panel
        panel.add(clothingLabel);
        panel.add(clothingField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(addButton);
        panel.add(viewButton);
        
        // Add the panel to the frame
        add(panel);
        
        // Set up the action listeners for the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = clothingField.getText();
                String price = priceField.getText();
                
                // Call your ClothingStore methods to add this item
                // Example: store.addItem(itemName, Double.parseDouble(price));
                
                JOptionPane.showMessageDialog(null, "Item added: " + itemName + " Price: " + price);
            }
        });
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to view all items, maybe display in a new window
                // Example: store.viewItems();
                JOptionPane.showMessageDialog(null, "Displaying all items...");
            }
        });
    }
    
    // Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RetailManagementGUI().setVisible(true);
            }
        });
    }
}

