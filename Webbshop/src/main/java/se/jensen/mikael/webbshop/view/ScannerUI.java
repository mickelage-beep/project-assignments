package se.jensen.mikael.webbshop.view;

import java.util.Scanner;

public class ScannerUI implements UI {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public void info(String message) {
        System.out.println(message);

    }

    @Override
    public String menu() {
        System.out.println("""
                Vad vill du göra?:
                1. Lägga till produkt
                2. Lista alla produkter
                3. Visa info om en produkt
                4. Ta bort alla produkter
                4. Avsluta
                """);
        return scanner.nextLine();
    }
}
