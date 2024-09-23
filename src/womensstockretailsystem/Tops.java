package womensstockretailsystem;

public class Tops extends Clothing {
    private String sleeveLength;

    public Tops(String size, String color, double price, String brand, String sleeveLength) {
        super(size, color, price, brand); 
        this.sleeveLength = sleeveLength;
    }

    @Override
    public void display() {
        System.out.println("Tops - Size: " + size + ", Color: " + color + ", Price: $" + price + ", Brand: " + brand + ", Sleeve Length: " + sleeveLength);
    }

    @Override
    public String toString() {
        return super.toString() + ", Sleeve Length: " + sleeveLength;
    }
}