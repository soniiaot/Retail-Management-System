package wretailsystem;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class ClothingStore {
    private LinkedList<Clothing> inventory;
//create a clothingstore linkedlist
    public ClothingStore() {
        inventory = new LinkedList<>();
    }
//add clothing method using a iterator to chekc if the item already exists
   public void addClothing(Clothing newClothing) {
        for (Clothing item : inventory) {
            if (item.matches(newClothing.size, newClothing.color, newClothing.brand) && item.matchesType(newClothing.getClass())) {
                item.increaseQuantity(newClothing.quantity);
                return;
            }
        }
        // If no matching item found, add as new
        ListIterator<Clothing> iterator = inventory.listIterator();
        while (iterator.hasNext()) {
            Clothing current = iterator.next();
            if (newClothing.getClass().getSimpleName().compareTo(current.getClass().getSimpleName()) < 0) {
                iterator.previous(); // Move back one to insert before
                break;
            }
        }
       //add new clothing and parse it to file
        iterator.add(newClothing);
        newClothing.saveToFile("inventory.txt");
    }
//remove method which is called from the main function, checks each part of the item to see if it matches
    public boolean removeClothing(String size, String color, String brand, Class<?> type, int quantity) {
        for (Clothing item : inventory) {
            if (item.matches(size, color, brand) && item.matchesType(type)) {
                item.decreaseQuantity(quantity);
                if (item.quantity == 0) {
                    inventory.remove(item);
                }
                return true;
            }
        }
        return false; // No matching item found
    }
//screen to show search clothing
public void searchClothing(Scanner scanner) {
    System.out.println("Select the type of clothing to search:");
    System.out.println("1. Lingerie");
    System.out.println("2. Knitwear");
    System.out.println("3. Tops");
    System.out.println("4. Pants");
    System.out.print("Choose an option: ");
    int typeChoice = scanner.nextInt();
    scanner.nextLine();  // Consume newline
//check if the input is valid
    Class<?> type = null;
    switch (typeChoice) {
        case 1 -> type = Lingerie.class;
        case 2 -> type = Knitwear.class;
        case 3 -> type = Tops.class;
        case 4 -> type = Pants.class;
        default -> {
            System.out.println("Invalid type choice.");
            return;
        }
    }
//check if results returns null (item does not exist)
    List<Clothing> results = search(null, null, null, type);
    if (results.isEmpty()) {
        System.out.println("No items found for the selected clothing type.");
        return;
    }
//printing the results
    System.out.println("Search results:");
    int i = 1;
    for (Clothing item : results) {
        System.out.println(i + ". " + item);
        i++;
    }
//check if user wants further filters
    System.out.print("Do you want to filter the results further by size, color, or brand? (yes/no): ");
    String filterChoice = scanner.nextLine().trim().toLowerCase();

    if (filterChoice.equals("yes")) {
        System.out.print("Size (or leave empty): ");
        String size = scanner.nextLine();
        System.out.print("Color (or leave empty): ");
        String color = scanner.nextLine();
        System.out.print("Brand (or leave empty): ");
        String brand = scanner.nextLine();

        results = search(size, color, brand, type);

        if (results.isEmpty()) {
            System.out.println("No matching clothing found.");
        } else {
            System.out.println("Filtered search results:");
            i = 1;
            for (Clothing item : results) {
                System.out.println(i + ". " + item);
                i++;
            }
        }
    }
}
//search function which is called by the searchclothing function
    public List<Clothing> search(String size, String color, String brand, Class<?> type) {
        List<Clothing> results = new LinkedList<>();
        for (Clothing item : inventory) {
            if ((type == null || item.matchesType(type)) && item.matches(size, color, brand)) {
                results.add(item);
            }
        }
        return results;
    }
  //print function to show entire inventory  
    public void displayInventory() {
        for (Clothing item : inventory) {
            item.display();
        }
    }

//validation methods to check that the input is within a certain range
public static int validateSize(Scanner scanner) {
        int size;
        while (true) {
            System.out.print("Enter Size (positive integer with max two digits): ");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (size > 0 && size <= 99) {
                    break;
                } else {
                    System.out.println("Size must be a positive integer with up to two digits.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid size.");
                scanner.next(); // Consume invalid input
            }
        }
        return size;
    }

    public static String validateString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.matches("[a-zA-Z ]+")) {  // Ensures only letters and spaces are allowed
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid string.");
            }
        }
        return input;
    }

    public static double validatePrice(Scanner scanner) {
        double price;
        while (true) {
            System.out.print("Enter Price (positive float): ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (price > 0) {
                    break;
                } else {
                    System.out.println("Price must be a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.next(); // Consume invalid input
            }
        }
        return price;
    }
//load the file when prorgam starts up
    public void loadInventoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Clothing clothing = parseClothing(line);
                if (clothing != null) {
                    inventory.add(clothing);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
//method to layput the inventory file into a certain style and parsing checks
 private Clothing parseClothing(String line) {
    try {
        // Split the line based on ", " which separates different attributes
        String[] parts = line.split(", ");

        
        // Extract and clean the type
        String type = parts[0].split(" - ")[0].trim();
        
        // Extract size from the first part
        String size = parts[0].split(": ")[1].trim();
        
        // Extract other attributes, making sure the key and value are properly split
        String color = parts[1].split(": ")[1].trim();
        String priceString = parts[2].split(": ")[1].trim().substring(1); // This should give us the price string without the $
        double price = Double.parseDouble(priceString); // Parse the price
        
        String brand = parts[3].split(": ")[1].trim();
        
        // Extract specific attribute (material, knit type, or sleeve length)
        String specificAttribute = parts[4].split(": ")[1].trim();
        
        // Extract quantity, which is at the end
        int quantity = Integer.parseInt(parts[5].split(": ")[1].trim());

        switch (type) {
            case "Lingerie":
                return new Lingerie(size, color, price, brand, specificAttribute, quantity);
            case "Knitwear":
                return new Knitwear(size, color, price, brand, specificAttribute, quantity);
            case "Tops":
                return new Tops(size, color, price, brand, specificAttribute, quantity);
            case "Pants":
                return new Pants(size, color, price, brand, specificAttribute, quantity);
            default:
                System.out.println("Unknown type: " + type);
                return null;
        }
    } catch (NumberFormatException e) {
        return null;
    } catch (Exception e) {     
        return null;
    }
}
//method to set a dsicount to a department 
public void setDiscount(double discountPercentage, Class<?> type) {
    for (Clothing item : inventory) {
        if (type == null || item.matchesType(type)) {
            item.setDiscount(discountPercentage);
        }
    }
    System.out.println("Discount applied successfully.");
}

}