package wretailsystem;
/*
This class is a layer between the user interface and ClothingService.
This class also has a discount by category method.
*/
import java.util.List;

public class ClothingUI {
    private ClothingService clothingService;

    public ClothingUI(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

public void createClothing(String name, String size, String color, double price, String brand, int quantity, String categoryID) {
    clothingService.createClothing(name, size, color, price, brand, quantity, categoryID);
}

    public ClothingItem getClothingByID(String clothingID) {
        return clothingService.getClothingByID(clothingID);
    }

public void updateClothing(String clothingID, String size, String color, double price, String brand, int quantity, String categoryID) {
    clothingService.updateClothing(clothingID, size, color, price, brand, quantity, categoryID);
}
    public boolean deleteClothing(String clothingID) {
        return clothingService.deleteClothing(clothingID);
    }

    public List<ClothingItem> getAllClothing() {
        return clothingService.getAllClothing();
    }
    
    public void applyDiscountByCategory(String categoryID, double discountPercentage) {
    List<ClothingItem> items = clothingService.getAllClothing();
    for (ClothingItem item : items) {
        if (item.getCategoryID().equals(categoryID)) {
            double newPrice = item.getPrice() * (1 - discountPercentage / 100);
            item.setPrice(newPrice);
            clothingService.updateClothing(item.getId(), item.getSize(), item.getColor(), newPrice, item.getBrand(), item.getQuantity(), categoryID);
        }
    }
}
    public List<ClothingItem> searchByName(String name) {
    return clothingService.searchByName(name);
}

    public List<ClothingItem> searchById(String id) {
    return clothingService.searchById(id);
}

public List<ClothingItem> searchBySize(String size) {
    return clothingService.searchBySize(size);
}

public List<ClothingItem> searchByColor(String color) {
    return clothingService.searchByColor(color);
}

public List<ClothingItem> searchByPrice(double price) {
    return clothingService.searchByPrice(price);
}

public List<ClothingItem> searchByBrand(String brand) {
    return clothingService.searchByBrand(brand);
}

public List<ClothingItem> searchByCategoryID(String categoryID) {
    return clothingService.searchByCategoryID(categoryID);
}

}