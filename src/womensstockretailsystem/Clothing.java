package womensstockretailsystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Clothing {
    protected String size;
    protected String color;
    protected double price;
    protected String brand;

    public Clothing(String size, String color, double price, String brand) {
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
    }

    public abstract void display();

    public boolean matches(String size, String color, String brand) {
        boolean sizeMatches = (size == null || this.size.equals(size));
        boolean colorMatches = (color == null || this.color.equals(color));
        boolean brandMatches = (brand == null || this.brand.equals(brand));
        return sizeMatches && colorMatches && brandMatches;
    }

    public boolean matchesType(Class<?> type) {
        return this.getClass().equals(type);
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(this.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " - Size: " + size + ", Color: " + color + ", Price: $" + price + ", Brand: " + brand;
    }
}