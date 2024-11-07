package wretailsystem;
/*
This class is a layer between the CategoryDAO class
and other parts of the application and deals with
category-related business logic. This class maintains
a in-memory list for quick access. This class ensures
that the changes are saved to the database through
CategoryDAO
*/
import java.util.List;
import java.util.ArrayList;

public class CategoryService {
    private List<Category> categories = new ArrayList<>();
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
        this.categories = categoryDAO.getAllCategories(); //Loads categories from the database
    }
    
    //Creates category from user input and adds it to the database
    public Category createCategory(String name) {
        Category category = new Category(name);
        categoryDAO.addCategory(category);
        categories.add(category);
        return category;
    }
//Gets teh category based on category id
    public Category getCategoryByID(String categoryID) {
        return categories.stream()
                         .filter(cat -> cat.getCategoryID().equals(categoryID))
                         .findFirst()
                         .orElse(null);
    }
//Updates category based on user input
    public Category updateCategory(String categoryID, String newName) {
        Category category = getCategoryByID(categoryID);
        if (category != null) {
            category.setCategoryName(newName);
            categoryDAO.updateCategory(category);  
        }
        return category;
    }

    public boolean deleteCategory(String categoryID) {
        Category category = getCategoryByID(categoryID);
        if (category != null) {
            categoryDAO.deleteCategory(categoryID);  
            return categories.remove(category);  
        }
        return false;
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }
}


