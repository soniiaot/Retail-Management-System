package wretailsystem;

import java.util.List;

public class ClothingUI {
    private ClothingService clothingService;

    public ClothingUI(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

public void createClothing(String size, String color, double price, String brand, int quantity, String categoryID) {
    clothingService.createClothing(size, color, price, brand, quantity, categoryID);
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
}
