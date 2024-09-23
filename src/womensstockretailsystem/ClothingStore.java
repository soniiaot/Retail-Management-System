package womensstockretailsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClothingStore {
    private List<Clothing> inventory;

    public ClothingStore() {
        inventory = new ArrayList<>();
    }

    public void addClothing(Clothing clothing) {
        inventory.add(clothing);
        clothing.saveToFile("inventory.txt");
    }

    public List<Clothing> search(String size, String color, String brand, Class<?> type) {
        List<Clothing> results = new ArrayList<>();
        for (Clothing item : inventory) {
            if ((type == null || item.matchesType(type)) && item.matches(size, color, brand)) {
                results.add(item);
            }
        }
        return results;
    }

    public void displayInventory() {
        for (Clothing item : inventory) {
            item.display();
        }
    }

    public void loadInventoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Clothing clothing = parseClothing(line);
                if (clothing != null) {
                    inventory.add(clothing);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private Clothing parseClothing(String line) {
        String[] parts = line.split(", ");
        String type = parts[0].split(" - ")[0];
        String size = parts[1].split(": ")[1];
        String color = parts[2].split(": ")[1];
        double price = Double.parseDouble(parts[3].split(": ")[1].substring(1));
        String brand = parts[4].split(": ")[1];

        switch (type) {
            case "Lingerie":
                String material = parts[5].split(": ")[1];
                return new Lingerie(size, color, price, brand, material);
            case "Knitwear":
                String knitType = parts[5].split(": ")[1];
                return new Knitwear(size, color, price, brand, knitType);
            case "Tops":
                String sleeveLength = parts[5].split(": ")[1];
                return new Tops(size, color, price, brand, sleeveLength);
            default:
                return null;
        }
    }
}

