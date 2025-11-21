package se.jensen.mikael.webbshop.controller;

import se.jensen.mikael.webbshop.model.Food;
import se.jensen.mikael.webbshop.model.Furniture;
import se.jensen.mikael.webbshop.model.Game;
import se.jensen.mikael.webbshop.model.Product;
import se.jensen.mikael.webbshop.view.JOptionPaneUI;
import se.jensen.mikael.webbshop.view.UI;

import java.util.List;

public class Main {

    private static final ProductList list = new ProductList();
    
    private static final UI ui = new JOptionPaneUI();

    public static void main(String[] args) {

        list.listProducts().addAll(FileHandler.loadProducts());

        showMenu();
    }


    private static void showMenu() {
        boolean go = true;

        ui.info("Välkommen till Webbshoppen!");

        while (go) {

            String menu = ui.menu();

            if (menu == null || menu.isBlank())
                break;

            int choice;

            try {
                choice = Integer.parseInt(menu);
            } catch (NumberFormatException e) {
                ui.info("Försök igen, ange en siffra.");
                continue;
            }

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> listProducts();
                case 3 -> showProductInfo();
                case 4 -> clearEverything();
                case 5 -> {
                    ui.info("Avslutar applikationen.");
                    go = false;
                }
                default -> ui.info("Ange en siffra 1-4, försök igen.");
            }
        }
    }

    private static void addProduct() {
        String type = ui.prompt("""
                Vad för produkt vill du lägga till?
                1. Game
                2. Food
                3. Furniture
                """);

        if (type == null)
            return;

        try {
            int input = Integer.parseInt(type);
            String titel = ui.prompt("Vad ska produkten heta? ");
            double price = Double.parseDouble(ui.prompt("Vad ska det kosta?"));
            String description = ui.prompt("Beskriv din produkt");
            String articleNum = ui.prompt("Vad ska varan ha för artikelnummer?");


            Product p = switch (input) {
                case 1 -> new Game(titel, price, description, articleNum);
                case 2 -> new Food(titel, price, description, articleNum);
                case 3 -> new Furniture(titel, price, description, articleNum);
                default -> null;
            };
            if (p != null) {
                list.addProduct(p);
                FileHandler.saveProduct(p);
            } else {
                ui.info("Ogiltig produktkategori ");
            }
        } catch (NumberFormatException e) {
            ui.info("Felaktig inmatning. Försök igen");
        }

    }

    private static void listProducts() {
        List<Product> products = list.listProducts();
        if (products.isEmpty()) {
            ui.info("Finns inga produkter inlagda.");
        } else {
            String all = "Alla produkter: \n";
            for (Product p : products) {
                all += p.toString() + "\n\n";
            }
            ui.info(all);


        }

    }

    private static void showProductInfo() {
        String input = ui.prompt("Vilken produkt vill du se mer om? \n" +
                "skriv titel eller artikelnummer");
        if (input == null) {
            return;
        }
        List<Product> finds = list.findProducts(input);
        if (finds.isEmpty()) {
            ui.info("Hitta ingen produkt för " + input);
        } else {
            String all = "";
            for (Product p : finds) {
                all += p.toStringAll() + "\n\n";
            }
            ui.info(all);

        }

    }

    private static void clearEverything() {
        String confirm = ui.prompt("Är du säker på att du vill ta bort alla produkter? ja/nej");
        if (confirm == null || !confirm.equalsIgnoreCase("ja")) {
            ui.info("Åtgärden avbröts");
            return;
        }
        FileHandler.clearProducts();
        list.listProducts().clear();
        ui.info("Alla produkter har tagits bort.");
    }

}
