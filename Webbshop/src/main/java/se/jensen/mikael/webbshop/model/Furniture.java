package se.jensen.mikael.webbshop.model;

public class Furniture extends Product{


    public Furniture(String title, double price, String description, String articleNumber) {
        super(title, price, description, articleNumber);
    }

    @Override
    public String category() {
        return "Furniture";
    }
}
