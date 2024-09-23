package womensstockretailsystem;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClothingStore store = new ClothingStore();
        store.loadInventoryFromFile("inventory.txt");  // Load existing inventory from file
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Women's Clothing Store");
            System.out.println("1. Add Lingerie");
            System.out.println("2. Add Knitwear");
            System.out.println("3. Add Tops");
            System.out.println("4. Search Clothing");
            System.out.println("5. Display Inventory");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    store.addClothing(createLingerie(scanner));
                    break;
                case 2:
                    store.addClothing(createKnitwear(scanner));
                    break;
                case 3:
                    store.addClothing(createTops(scanner));
                    break;
                case 4:
                    searchClothing(store, scanner);
                    break;
                case 5:
                    store.displayInventory();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
        scanner.close();
    }

    private static Lingerie createLingerie(Scanner scanner) {
        System.out.println("Enter Lingerie details:");
        System.out.print("Size: ");
        String size = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Material: ");
        String material = scanner.nextLine();
        return new Lingerie(size, color, price, brand, material);
    }

    private static Knitwear createKnitwear(Scanner scanner) {
        System.out.println("Enter Knitwear details:");
        System.out.print("Size: ");
        String size = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Knit Type: ");
        String knitType = scanner.nextLine();
        return new Knitwear(size, color, price, brand, knitType);
    }

    private static Tops createTops(Scanner scanner) {
        System.out.println("Enter Tops details:");
        System.out.print("Size: ");
        String size = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Sleeve Length: ");
        String sleeveLength = scanner.nextLine();
        return new Tops(size, color, price, brand, sleeveLength);
    }

    private static void searchClothing(ClothingStore store, Scanner scanner) {
        System.out.println("Enter search criteria:");
        System.out.print("Size (or leave empty): ");
        String size = scanner.nextLine();
        System.out.print("Color (or leave empty): ");
        String color = scanner.nextLine();
        System.out.print("Brand (or leave empty): ");
        String brand = scanner.nextLine();
        System.out.println("Select type of clothing to search:");
        System.out.println("1. Lingerie");
        System.out.println("2. Knitwear");
        System.out.println("3. Tops");
        System.out.print("Choose an option: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Class<?> type = null;
        switch (typeChoice) {
            case 1:
                type = Lingerie.class;
                break;
            case 2:
                type = Knitwear.class;
                break;
            case 3:
                type = Tops.class;
                break;
            default:
                System.out.println("Invalid type choice.");
                return;
        }

        List<Clothing> results = store.search(size, color, brand, type);
        if (results.isEmpty()) {
            System.out.println("No matching clothing found.");
        } else {
            System.out.println("Search results:");
            for (Clothing item : results) {
                item.display();
            }
        }
    }
}
