package se.jensen.mikael.webbshop.view;

import javax.swing.*;

public class JOptionPaneUI implements UI{
    @Override
    public String prompt(String message) {
        return JOptionPane.showInputDialog(message);
    }

    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message);

    }

    @Override
    public String menu() {
        return JOptionPane.showInputDialog("""
                Vad vill du göra?:
                1. Lägga till produkt
                2. Lista alla produkter
                3. Visa info om en produkt
                4. Ta bort alla produkter
                5. Avsluta
                """);
    }
}
