/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fancy
 */
package Test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import wretailsystem.Category;
import wretailsystem.CategoryDAO;
import wretailsystem.CategoryService;
import wretailsystem.ClothingItem;
import wretailsystem.ClothingDAO;
import wretailsystem.ClothingService;

public class ServiceTests {

    private CategoryService categoryService;
    private ClothingService clothingService;

    @Before
    public void setUp() {
        // Initialize DAO and Service objects
        CategoryDAO categoryDAO = new CategoryDAO();
        ClothingDAO clothingDAO = new ClothingDAO();
        categoryService = new CategoryService(categoryDAO);
        clothingService = new ClothingService(clothingDAO);
    }

    @Test
    public void testCreateCategory() {
        Category newCategory = categoryService.createCategory("Tops");
        assertNotNull("Category should not be null", newCategory);
        assertEquals("Tops", newCategory.getCategoryName());
        assertNotNull("Category ID should not be null", newCategory.getCategoryID());
    }

    @Test
    public void testGetCategoryByID() {
        Category newCategory = categoryService.createCategory("Tops");
        Category retrievedCategory = categoryService.getCategoryByID(newCategory.getCategoryID());
        assertNotNull("Retrieved category should not be null", retrievedCategory);
        assertEquals("Tops", retrievedCategory.getCategoryName());
    }

    @Test
    public void testUpdateCategory() {
        Category newCategory = categoryService.createCategory("Tops");
        String categoryID = newCategory.getCategoryID();
        categoryService.updateCategory(categoryID, "Shirts");
        Category updatedCategory = categoryService.getCategoryByID(categoryID);
        assertEquals("Shirts", updatedCategory.getCategoryName());
    }

    @Test
    public void testDeleteCategory() {
        Category newCategory = categoryService.createCategory("Tops");
        boolean deleted = categoryService.deleteCategory(newCategory.getCategoryID());
        assertTrue("Category should be deleted", deleted);
        assertNull("Deleted category should be null when retrieved", 
                   categoryService.getCategoryByID(newCategory.getCategoryID()));
    }

   @Test
    public void testAddClothingItemWithCategory() {
        Category newCategory = categoryService.createCategory("Tops");
        ClothingItem clothingItem = clothingService.createClothing("M", "Red", 29.99, "BrandX", 10, newCategory.getCategoryID());
        assertNotNull("Clothing item should not be null", clothingItem);
        assertEquals("M", clothingItem.getSize());
        assertEquals("Red", clothingItem.getColor());
        assertEquals("BrandX", clothingItem.getBrand());
        assertEquals(29.99, clothingItem.getPrice(), 0.01);
        assertEquals(newCategory.getCategoryID(), clothingItem.getCategoryID());
    }
}
