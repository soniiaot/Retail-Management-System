package wretailsystem;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Category {
    private String categoryID;
    private String categoryName;
    private List<ClothingItem> clothingItems = new ArrayList<>();

    public Category() {}

    public Category(String categoryName) {
        this.categoryID = UUID.randomUUID().toString();
        this.categoryName = categoryName;
    }

    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
