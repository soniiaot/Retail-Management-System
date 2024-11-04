package wretailsystem;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Category {
    private String categoryID;
    private String categoryName;
    private List<ClothingItem> clothingItems = new ArrayList<>(); // Stores ClothingItem objects

    // Default constructor
    public Category() {}

    // Parameterized constructor
    public Category(String categoryName) {
        this.categoryID = UUID.randomUUID().toString();  // Auto-generate a unique ID
        this.categoryName = categoryName;
    }

    // Getter for category ID
    public String getCategoryID() {
        return categoryID;
    }

    // Getter and setter for category name
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Get a copy of all clothing items in this category
    public List<ClothingItem> getClothingItems() {
        return new ArrayList<>(clothingItems);
    }

    // Add a new clothing item to this category
    public void addClothingItem(ClothingItem clothingItem) {
        if (clothingItem != null && !clothingItems.contains(clothingItem)) {
            clothingItems.add(clothingItem);
        }
    }

    // Remove a clothing item from this category
    public void removeClothingItem(ClothingItem clothingItem) {
        clothingItems.remove(clothingItem);
    }

    // Display all items in the category
    public void displayItems() {
        System.out.println("Category: " + categoryName);
        for (ClothingItem item : clothingItems) {
            item.display();
        }
    }
}


