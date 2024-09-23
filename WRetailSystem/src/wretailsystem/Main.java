package wretailsystem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         Password passwordManager = new Password();
        Scanner scanner = new Scanner(System.in);
        boolean authenticated = false;

        //Welcome prompt
        System.out.println("***** Welcome to the Women's Clothing Store System! *****\n");
        
        // Authentication loop
        while (!authenticated) {           
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Authenticate the user
            authenticated = passwordManager.authenticate(username, password);

            if (!authenticated) {
                System.out.println("\nInvalid username or password. Please try again.\n");
            }
        }
ClothingStore store = new ClothingStore();
        store.loadInventoryFromFile("inventory.txt");  // Load existing inventory from file
        boolean running = true;
//main menu screen
while (running) {
            System.out.println("\nWelcome to the Women's Clothing Store");
            System.out.println("1. Add Lingerie");
            System.out.println("2. Add Knitwear");
            System.out.println("3. Add Tops");
            System.out.println("4. Add Pants");
            System.out.println("5. Search Clothing");
            System.out.println("6. Display Inventory");
            System.out.println("7. Set Discount by Department");
            System.out.println("8. Remove Clothing");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
//choices switch cases
                switch (choice) {
                    case 1 -> store.addClothing(Lingerie.createClothing(scanner));
                    case 2 -> store.addClothing(Knitwear.createClothing(scanner));
                    case 3 -> store.addClothing(Tops.createClothing(scanner));
                    case 4 -> store.addClothing(Pants.createClothing(scanner));
                    case 5 -> store.searchClothing(scanner);
                    case 6 -> store.displayInventory();
                    case 7 -> setDiscountByDepartmentUI(store, scanner);
                    case 8 -> {
                        removeClothingUI(store, scanner);
                    }
                    case 9 -> running = false;
                    default -> System.out.println("Invalid option. Please choose again.");
                }
            } catch (InputMismatchException e){//prevent software from crashing 
                System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
                scanner.nextLine();  // Clear the invalid input from the scanner
            }
        }

        scanner.close();
    }
//remove clothing output questions
private static void removeClothingUI(ClothingStore store, Scanner scanner) {
    System.out.println("Select type of clothing to remove:");
    System.out.println("1. Lingerie");
    System.out.println("2. Knitwear");
    System.out.println("3. Tops");
    System.out.println("4. Pants");
    System.out.print("Choose an option: ");
    int typeChoice = scanner.nextInt();
    scanner.nextLine();  // Consume newline

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

    List<Clothing> results = store.search(null, null, null, type);
    if (results.isEmpty()) {
        System.out.println("No matching clothing found.");
        return;
    }

    System.out.println("Select an item to remove:");
    for (int i = 0; i < results.size(); i++) {
        System.out.println((i + 1) + ". " + results.get(i).toString());
    }
    System.out.print("Enter the number of the item to remove: ");
    int itemNumber = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (itemNumber < 1 || itemNumber > results.size()) {
        System.out.println("Invalid item number.");
        return;
    }

    Clothing selectedItem = results.get(itemNumber - 1);

    System.out.print("Enter quantity to remove: ");
    int quantity = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (store.removeClothing(selectedItem.size, selectedItem.color, selectedItem.brand, type, quantity)) {
        System.out.println("Clothing item quantity updated successfully.");
    } else {
        System.out.println("Error: Could not remove the specified quantity.");
    }
}
//set discount output questions
private static void setDiscountByDepartmentUI(ClothingStore store, Scanner scanner) {
    System.out.println("Select the department to apply a discount:");
    System.out.println("1. Lingerie");
    System.out.println("2. Knitwear");
    System.out.println("3. Tops");
    System.out.println("4. Pants");
    System.out.print("Choose an option: ");
    int typeChoice = scanner.nextInt();
    scanner.nextLine();  // Consume newline

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

    System.out.print("Enter the discount percentage: ");
    double discountPercentage = scanner.nextDouble();
    scanner.nextLine();  // Consume newline

    store.setDiscount(discountPercentage, type);
}

}