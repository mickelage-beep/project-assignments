package se.jensen.mikael.dicegame;

import java.util.Random;

public class Dice {

    private Random random = new Random();

    public int roll() {
        return random.nextInt(1, 7);
    }
}
