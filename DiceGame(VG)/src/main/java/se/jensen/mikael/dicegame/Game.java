package se.jensen.mikael.dicegame;

import javax.swing.JOptionPane;

public class Game {
    public void start(){
        Player player1 = new Player();
        Player player2 = new Player();
        Dice dice = new Dice();

        while (true){
            try {
                player1.setFirstName(JOptionPane.showInputDialog("Spelare1: Skriv in förnamn."));
                player1.setLastName(JOptionPane.showInputDialog("Spelare1: Skriv in efternamn"));
                break;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        while (true){
            try {
                player2.setFirstName(JOptionPane.showInputDialog("Spelare2: Skriv in förnamn."));
                player2.setLastName(JOptionPane.showInputDialog("Spelare2: Skriv in efternamn"));
                break;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }


        boolean playing = true;

        while (playing){
            player1.resetScore();
            player2.resetScore();
            String input = JOptionPane.showInputDialog(
                    "Skriv 'play' för att spela eller 'quit' för att avsluta").toLowerCase();

            for (int i = 0; i < 2; i++) {
                player1.addToScore(dice.roll());
                player2.addToScore(dice.roll());

            }

            if (input.equals("play")) {

                String scoreMessage = player1.getFullName() + " fick " + player1.getScore() + "poäng.\n"
                        + player2.getFullName() + " fick " + player2.getScore() + "poäng.\n";

                JOptionPane.showMessageDialog(null, scoreMessage);

                if (player1.getScore() < player2.getScore()) {
                    JOptionPane.showMessageDialog(null, player2.getFirstName() + " vann över " + player1.getLastName() + "!!");
                } else if (player1.getScore() > player2.getScore()) {
                    JOptionPane.showMessageDialog(null,player1.getFirstName() + " vann över " + player2.getLastName() + "!!");

                } else {
                    JOptionPane.showMessageDialog(null,"Det blev oavgjort!");
                }

            }else if (input.equals("quit")) {
                playing = false;
                JOptionPane.showMessageDialog(null,"Hejdå!");
                ;
            }else {
                JOptionPane.showMessageDialog(null,"Du måste skriva quit eller play");
            }


        }

    }
}
