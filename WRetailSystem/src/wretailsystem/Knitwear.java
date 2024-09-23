package wretailsystem;
import java.util.Scanner;


public class Knitwear extends Clothing {


    private String knitType;

    public Knitwear(String size, String color, double price, String brand, String knitType, int quantity) {
        super(size, color, price, brand, quantity); 
        this.knitType = knitType;
    }
    //output screen to add
    public static Knitwear createClothing(Scanner scanner) {
        System.out.println("Enter Knitwear details:");
        int size = ClothingStore.validateSize(scanner);
        String color = ClothingStore.validateString(scanner, "Enter Color: ");
        double price = ClothingStore.validatePrice(scanner);
        String brand = ClothingStore.validateString(scanner, "Enter Brand: ");
        System.out.print("Knit Type: ");
        String knitType = scanner.nextLine();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        return new Knitwear(String.valueOf(size), color, price, brand, knitType, quantity);
    }
    @Override
    public String toString() {
        return super.toString() + ", Knit Type: " + knitType;
    } 
    @Override
    public void display() {
        System.out.println(toString());
    }
}