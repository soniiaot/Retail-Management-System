package wretailsystem;
/*
This class is responsible for managing clothing items. Its a layer
bettwen the ClothingDAO class and maintains an in-memory list of
clothing items to make it easy to access. 
*/

import java.util.ArrayList;
import java.util.List;

public class ClothingService {
    private List<ClothingItem> clothingItems = new ArrayList<>();
    private final ClothingDAO clothingDAO;

    public ClothingService(ClothingDAO clothingDAO) {
        this.clothingDAO = clothingDAO;
        this.clothingItems = clothingDAO.getAllClothing(); // Load all items from database on startup
    }

public ClothingItem createClothing(String name, String size, String color, double price, String brand, int quantity, String categoryID) {
    ClothingItem clothing = new ClothingItem(name, size, color, price, brand, quantity, categoryID);
    clothingDAO.addClothing(clothing);
    clothingItems.add(clothing);
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
public List<ClothingItem> searchByName(String name) {
    List<ClothingItem> result = new ArrayList<>();
    for (ClothingItem item : clothingItems) {
        if (item.getName().equalsIgnoreCase(name)) {
            result.add(item);
        }
    }
    return result;
}

public List<ClothingItem> searchById(String id) {
    List<ClothingItem> result = new ArrayList<>();
    for (ClothingItem item : clothingItems) {
        if (item.getId().equals(id)) {
            result.add(item);
        }
    }
    return result;
}

public List<ClothingItem> searchBySize(String size) {
    List<ClothingItem> result = new ArrayList<>();
    for (ClothingItem item : clothingItems) {
        if (item.getSize().equalsIgnoreCase(size)) {
            result.add(item);
        }
    }
    return result;
}

public List<ClothingItem> searchByColor(String color) {
    List<ClothingItem> result = new ArrayList<>();
    for (ClothingItem item : clothingItems) {
        if (item.getColor().equalsIgnoreCase(color)) {
            result.add(item);
        }
    }
    return result;
}

public List<ClothingItem> searchByPrice(double price) {
    List<ClothingItem> result = new ArrayList<>();
    for (ClothingItem item : clothingItems) {
        if (item.getPrice() == price) {
            result.add(item);
        }
    }
    return result;
}

public List<ClothingItem> searchByBrand(String brand) {
    List<ClothingItem> result = new ArrayList<>();
    for (ClothingItem item : clothingItems) {
        if (item.getBrand().equalsIgnoreCase(brand)) {
            result.add(item);
        }
    }
    return result;
}

public List<ClothingItem> searchByCategoryID(String categoryID) {
    List<ClothingItem> result = new ArrayList<>();
    for (ClothingItem item : clothingItems) {
        if (item.getCategoryID().equals(categoryID)) {
            result.add(item);
        }
    }
    return result;
}

}

