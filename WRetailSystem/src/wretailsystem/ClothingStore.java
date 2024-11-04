package wretailsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClothingStore {
    private List<ClothingItem> inventory = new ArrayList<>();

    // Add clothing item to inventory
    public void addClothing(ClothingItem clothing) {
        inventory.add(clothing);
        System.out.println("Clothing item added: " + clothing.toString());
    }

    // Search for clothing items by attributes
    public void searchClothing(String size, String color, String brand) {
        for (ClothingItem item : inventory) {
            if (item.matches(size, color, brand)) {
                item.display();
            }
        }
    }

    // Display all clothing items
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (ClothingItem item : inventory) {
                item.display();
            }
        }
    }

    // Remove clothing item by brand
    public boolean removeClothing(String brand) {
        return inventory.removeIf(item -> item.getBrand().equals(brand));
    }

    // Apply a discount to all items of a specific brand
    public void applyDiscountByBrand(String brand, double discountPercentage) {
        for (ClothingItem item : inventory) {
            if (item.getBrand().equals(brand)) {
                item.applyDiscount(discountPercentage);
            }
        }
        System.out.println("Discount applied to all items of brand: " + brand);
    }
}

