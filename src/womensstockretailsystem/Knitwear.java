package womensstockretailsystem;

public class Knitwear extends Clothing {
    private String knitType;

    public Knitwear(String size, String color, double price, String brand, String knitType) {
        super(size, color, price, brand); 
        this.knitType = knitType;
    }

    @Override
    public void display() {
        System.out.println("Knitwear - Size: " + size + ", Color: " + color + ", Price: $" + price + ", Brand: " + brand + ", Knit Type: " + knitType);
    }

    @Override
    public String toString() {
        return super.toString() + ", Knit Type: " + knitType;
    }
}