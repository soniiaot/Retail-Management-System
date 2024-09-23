package wretailsystem;

import java.util.Scanner;

public class Lingerie extends Clothing {
    private String material;

   public Lingerie(String size, String color, double price, String brand, String material, int quantity) {
        super(size, color, price, brand, quantity);
        this.material = material;
    }
//output for create method
     public static Lingerie createClothing(Scanner scanner) {
        System.out.println("Enter Lingerie details:");
        int size = ClothingStore.validateSize(scanner);
        String color = ClothingStore.validateString(scanner, "Enter Color: ");
        double price = ClothingStore.validatePrice(scanner);
        String brand = ClothingStore.validateString(scanner, "Enter Brand: ");
        System.out.print("Material: ");
        String material = scanner.nextLine();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return new Lingerie(String.valueOf(size), color, price, brand, material, quantity);
    }
 @Override
    public String toString() {
        return super.toString() + ", Material: " + material;
    }
    @Override
    public void display() {
        System.out.println(toString());
    }
    
}