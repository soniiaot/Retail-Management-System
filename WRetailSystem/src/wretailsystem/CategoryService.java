/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wretailsystem;

/**
 *
 * @author fancy
 */
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private List<Category> categories = new ArrayList<>();

    public Category createCategory(String name) {
        Category category = new Category(name);
        categories.add(category);
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
        }
        return category;
    }

    public boolean deleteCategory(String categoryID) {
        return categories.removeIf(cat -> cat.getCategoryID().equals(categoryID));
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }
}

