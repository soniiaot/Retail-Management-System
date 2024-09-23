package wretailsystem;

import java.util.Scanner;

public class Pants extends Clothing {
    private String fitType;

    public Pants(String size, String color, double price, String brand, String fitType, int quantity) {
        super(size, color, price, brand, quantity);
        this.fitType = fitType;
    }
//output for create pants method
    public static Pants createClothing(Scanner scanner) {
        System.out.println("Enter Pants details:");
        int size = ClothingStore.validateSize(scanner);
        String color = ClothingStore.validateString(scanner, "Enter Color: ");
        double price = ClothingStore.validatePrice(scanner);
        String brand = ClothingStore.validateString(scanner, "Enter Brand: ");
        System.out.print("Fit Type (e.g., skinny, baggy): ");
        String fitType = scanner.nextLine();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return new Pants(String.valueOf(size), color, price, brand, fitType, quantity);
    }

    @Override
    public String toString() {
        return super.toString() + ", Fit Type: " + fitType;
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

}