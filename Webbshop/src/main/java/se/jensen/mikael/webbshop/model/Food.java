package se.jensen.mikael.webbshop.model;

public class Food extends Product{


    public Food(String title, double price, String description, String articleNumber) {
        super(title, price, description, articleNumber);
    }

    @Override
    public String category() {
        return "Food";
    }
}
