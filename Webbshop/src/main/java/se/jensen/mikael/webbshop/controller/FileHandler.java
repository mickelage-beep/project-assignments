package se.jensen.mikael.webbshop.controller;

import se.jensen.mikael.webbshop.model.*;
import se.jensen.mikael.webbshop.view.JOptionPaneUI;
import se.jensen.mikael.webbshop.view.UI;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final UI ui = new JOptionPaneUI();
    private static final Path file = Path.of("data/products.txt");

    static {
        try {
            if (Files.notExists(file)) {
                Files.createDirectories(file.getParent());
                Files.createFile(file);
            }
        } catch (IOException e) {
            ui.info("Fel vid skapande av fil: " + e.getMessage());
        }
    }

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(file);

            for (String line : lines) {
                if (line.isBlank())
                    continue;

                String[] parts = line.split(";");
                if (parts.length != 5)
                    continue;

                String type = parts[0];
                String title = parts[1];
                double price = Double.parseDouble(parts[2]);
                String description = parts[3];
                String articleNumber = parts[4];

                Product product = switch (type) {
                    case "Game" -> new Game(title, price, description, articleNumber);
                    case "Food" -> new Food(title, price, description, articleNumber);
                    case "Furniture" -> new Furniture(title, price, description, articleNumber);
                    default -> null;
                };

                if (product != null)
                    products.add(product);
            }
        } catch (IOException e) {
            ui.info("Fel vid inläsning av fil: " + e.getMessage());
        } catch (NumberFormatException e) {
            ui.info("Felaktigt prisformat i filen. " + e.getMessage());
        }

        return products;
    }

    public static void saveProduct(Product product) {
        try {
            Files.writeString(
                    file,
                    product.toFileString() + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            ui.info("Fel vid sparning av produkt: " + e.getMessage());
        }
    }

    public static void clearProducts() {
        try {
            Files.writeString(file, "");
            ui.info("Alla produkter har raderats från webbshoppen.");
        } catch (IOException e) {
            ui.info("Fel vid rensning av fil: " + e.getMessage());
        }
    }
}
