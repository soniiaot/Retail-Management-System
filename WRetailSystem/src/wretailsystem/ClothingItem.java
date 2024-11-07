package wretailsystem;
/*
This class defines the ClothingItem object and its get and set methods
*/
import java.util.UUID;

public class ClothingItem {
    private String id;
    private String name; // New name attribute
    private String size;
    private String color;
    private double price;
    private String brand;
    private int quantity;
    private String categoryID;

    public ClothingItem(String name, String size, String color, double price, String brand, int quantity, String categoryID) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    public ClothingItem(String id, String name, String size, String color, double price, String brand, int quantity, String categoryID) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    // Add getters and setters for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
