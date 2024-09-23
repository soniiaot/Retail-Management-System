package womensstockretailsystem;

public class Lingerie extends Clothing {
    private String material;

    public Lingerie(String size, String color, double price, String brand, String material) {
        super(size, color, price, brand); 
        this.material = material;
    }

    @Override
    public void display() {
        System.out.println("Lingerie - Size: " + size + ", Color: " + color + ", Price: $" + price + ", Brand: " + brand + ", Material: " + material);
    }

    @Override
    public String toString() {
        return super.toString() + ", Material: " + material;
    }
}