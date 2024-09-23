package wretailsystem;

import java.util.Scanner;

public class Tops extends Clothing {
    private String sleeveLength;

    public Tops(String size, String color, double price, String brand, String sleeveLength, int quantity) {
        super(size, color, price, brand, quantity); 
        this.sleeveLength = sleeveLength;
    }
//output for create tops method    
public static Tops createClothing(Scanner scanner) {
        System.out.println("Enter Tops details:");
        int size = ClothingStore.validateSize(scanner);
        String color = ClothingStore.validateString(scanner, "Enter Color: ");
        double price = ClothingStore.validatePrice(scanner);
        String brand = ClothingStore.validateString(scanner, "Enter Brand: ");
        System.out.print("Sleeve Length: ");
        String sleeveLength = scanner.nextLine();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        return new Tops(String.valueOf(size), color, price, brand, sleeveLength, quantity);
    }

@Override
    public String toString() {
        return super.toString() + ", Sleeve Length: " + sleeveLength;
    }
    @Override
    public void display() {
        System.out.println(toString());
    }

}