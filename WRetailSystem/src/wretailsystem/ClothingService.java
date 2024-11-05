package wretailsystem;

import java.util.ArrayList;
import java.util.List;

public class ClothingService {
    private List<ClothingItem> clothingItems = new ArrayList<>();
    private final ClothingDAO clothingDAO;

    public ClothingService(ClothingDAO clothingDAO) {
        this.clothingDAO = clothingDAO;
        this.clothingItems = clothingDAO.getAllClothing(); // Load all items from database on startup
    }

    public ClothingItem createClothing(String size, String color, double price, String brand, int quantity, String categoryID) {
        ClothingItem clothing = new ClothingItem(size, color, price, brand, quantity, categoryID);
        clothingDAO.addClothing(clothing);
        clothingItems.add(clothing);  // Add to in-memory list
        return clothing;
    }

    public ClothingItem getClothingByID(String clothingID) {
        return clothingItems.stream()
                            .filter(item -> item.getId().equals(clothingID))
                            .findFirst()
                            .orElse(null);
    }

    public ClothingItem updateClothing(String clothingID, String size, String color, double price, String brand, int quantity, String categoryID) {
        ClothingItem clothing = getClothingByID(clothingID);
        if (clothing != null) {
            clothing.setSize(size);
            clothing.setColor(color);
            clothing.setPrice(price);
            clothing.setBrand(brand);
            clothing.setQuantity(quantity);
            clothing.setCategoryID(categoryID);  // Update category ID
            clothingDAO.updateClothing(clothing);  // Update in database
        }
        return clothing;
    }

    public boolean deleteClothing(String clothingID) {
        ClothingItem clothing = getClothingByID(clothingID);
        if (clothing != null) {
            clothingDAO.deleteClothing(clothingID);  // Delete from database
            return clothingItems.remove(clothing);  // Remove from in-memory list
        }
        return false;
    }

    public List<ClothingItem> getAllClothing() {
        return new ArrayList<>(clothingItems);
    }
}

