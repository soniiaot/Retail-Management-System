package wretailsystem;

import java.util.List;
import java.util.ArrayList;

public class CategoryService {
    private List<Category> categories = new ArrayList<>();
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
        this.categories = categoryDAO.getAllCategories(); // Load all categories from database on startup
    }

    public Category createCategory(String name) {
        Category category = new Category(name);
        categoryDAO.addCategory(category);
        categories.add(category);  // Add to in-memory list
        return category;
    }

    public Category getCategoryByID(String categoryID) {
        return categories.stream()
                         .filter(cat -> cat.getCategoryID().equals(categoryID))
                         .findFirst()
                         .orElse(null);
    }

    public Category updateCategory(String categoryID, String newName) {
        Category category = getCategoryByID(categoryID);
        if (category != null) {
            category.setCategoryName(newName);
            categoryDAO.updateCategory(category);  // Update in database
        }
        return category;
    }

    public boolean deleteCategory(String categoryID) {
        Category category = getCategoryByID(categoryID);
        if (category != null) {
            categoryDAO.deleteCategory(categoryID);  // Delete from database
            return categories.remove(category);  // Remove from in-memory list
        }
        return false;
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }
}


