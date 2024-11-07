package wretailsystem;
/*
This class just simiplifies interactions for category related functions
*/
import java.util.List;

public class CategoryUI {
    private CategoryService categoryService;

    public CategoryUI(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category createCategory(String name) {
        return categoryService.createCategory(name);
    }

    public Category getCategoryByID(String categoryID) {
        return categoryService.getCategoryByID(categoryID);
    }

    public Category updateCategory(String categoryID, String newName) {
        return categoryService.updateCategory(categoryID, newName);
    }

    public boolean deleteCategory(String categoryID) {
        return categoryService.deleteCategory(categoryID);
    }

    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
