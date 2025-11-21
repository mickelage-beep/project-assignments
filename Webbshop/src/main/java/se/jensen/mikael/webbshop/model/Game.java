package se.jensen.mikael.webbshop.model;

public class Game extends Product {


    public Game(String title, double price, String description, String articleNumber) {
        super(title, price, description, articleNumber);
    }

    @Override
    public String category() {
        return "Game";

    }
}
