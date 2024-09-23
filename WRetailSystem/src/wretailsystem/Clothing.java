package wretailsystem;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//clothing object which repersents each item in the store- its abstract as it could be anything
public abstract class Clothing {
    protected String size;
    protected String color;
    protected double price;
    protected String brand;
    protected int quantity;

     public Clothing(String size, String color, double price, String brand, int quantity) {
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        
    }
//all child classes need to be able to display
    public abstract void display();

 //checks if each aspect of the clothing matches for search functions   
    public boolean matches(String size, String color, String brand) {
    boolean sizeMatches = (size == null || size.isEmpty() || this.size.equals(size));
    boolean colorMatches = (color == null || color.isEmpty() || this.color.equals(color));
    boolean brandMatches = (brand == null || brand.isEmpty() || this.brand.equals(brand));
    return sizeMatches && colorMatches && brandMatches;
}

    public boolean matchesType(Class<?> type) {
        return this.getClass().equals(type);
    }
 //increase method for add method   
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
//decrease for remove
    public void decreaseQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            System.out.println("Error: Not enough stock to remove the requested quantity.");
        }
    }
//file writing method
  public void saveToFile(String filename) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(this.toString());
        writer.newLine();
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}
//toString format    
@Override
public String toString() {
    return this.getClass().getSimpleName() + " - Size: " + size + ", Color: " + color + ", Price: $" + price + ", Brand: " + brand + ", Quantity: " + quantity;
}
//logic to apply disocunt
public void setDiscount(double discountPercentage) {
    this.price *= (1 - discountPercentage / 100);
}

}