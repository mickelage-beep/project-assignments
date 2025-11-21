package se.jensen.mikael.dicegame;

import javax.swing.JOptionPane;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Player player1 = new Player(JOptionPane.showInputDialog(
                "Spelare1: Skriv in ditt förnamn"),
                JOptionPane.showInputDialog(
                        "Spelare1: Skriv in ditt eftnamn")
        );
        Player player2 = new Player(JOptionPane.showInputDialog(
                "Spelare2: Skriv in ditt förnamn"),
                JOptionPane.showInputDialog(
                        "Spelare2: Skriv in ditt eftnamn")
        );
        String input = JOptionPane.showInputDialog(
                "Skriv 'play' för att spela eller 'quit' för att avsluta").toLowerCase();

        if (input.equals("play")) {
            for (int i = 0; i < 2; i++) {
                player1.addToScore(random.nextInt(1, 7));
                player2.addToScore(random.nextInt(1, 7));
            }

            String scoreMessage = player1.getFullName() + " fick " + player1.getScore() + "poäng.\n"
                    + player2.getFullName() + " fick " + player2.getScore() + "poäng.\n";

            System.out.println(scoreMessage);

            if (player1.getScore() < player2.getScore()) {
                System.out.println(player2.getFirstName() + " vann över " + player1.getLastName() + "!!");
            } else if (player1.getScore() > player2.getScore()) {
                System.out.println(player1.getFirstName() + " vann över " + player2.getLastName() + "!!");

            } else {
                System.out.println("Det blev oavgjort!");
            }


        }else {
            System.out.println("Okey hejdå!");

        }


    }
}
