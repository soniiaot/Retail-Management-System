package wretailsystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class ClothingItem {
    private String size;
    private String color;
    private double price;
    private String brand;
    private int quantity;
    private String id; // Unique identifier

    // Constructor with automatic UUID generation for id
    public ClothingItem(String size, String color, double price, String brand, int quantity) {
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.id = UUID.randomUUID().toString(); // Generate unique ID
    }

    // Display clothing item details
    public void display() {
        System.out.println(this.toString());
    }

    // Check if this item matches search criteria
    public boolean matches(String size, String color, String brand) {
        boolean sizeMatches = (size == null || size.isEmpty() || this.size.equals(size));
        boolean colorMatches = (color == null || color.isEmpty() || this.color.equals(color));
        boolean brandMatches = (brand == null || brand.isEmpty() || this.brand.equals(brand));
        return sizeMatches && colorMatches && brandMatches;
    }

    // Override toString method for detailed display including the ID
    @Override
    public String toString() {
        return "ID: " + id + ", Size: " + size + ", Color: " + color +
               ", Price: $" + price + ", Brand: " + brand + ", Quantity: " + quantity;
    }

    // Apply a discount to this item
    public void applyDiscount(double discountPercentage) {
        this.price *= (1 - discountPercentage / 100);
    }

    // Getters
    public String getId() { return id; } // Added getter for id
    public String getSize() { return size; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public String getBrand() { return brand; }
    public int getQuantity() { return quantity; }

    // File-saving method for optional file-based storage
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(this.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
