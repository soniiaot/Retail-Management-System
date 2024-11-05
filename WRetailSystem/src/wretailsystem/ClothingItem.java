package wretailsystem;

import java.util.UUID;

public class ClothingItem {
    private String id;
    private String size;
    private String color;
    private double price;
    private String brand;
    private int quantity;
    private String categoryID;

    public ClothingItem(String size, String color, double price, String brand, int quantity, String categoryID) {
        this.id = UUID.randomUUID().toString(); // Auto-generate ID
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    // Constructor for loading existing items from database
    public ClothingItem(String id, String size, String color, double price, String brand, int quantity, String categoryID) {
        this.id = id;
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
