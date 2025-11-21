package se.jensen.mikael.webbshop.model;

public abstract class Product {

    private String title;
    private double price;
    private String description;
    private String articleNumber;


    public Product(String title, double price, String description, String articleNumber) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.articleNumber = articleNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public abstract String category();


    @Override
    public String toString() {
        return "Title: " + title +
                "\nPrice: " + price +
                "\nArtikelnummer: " + articleNumber;
    }

    public String toStringAll() {
        return "Titel: " + title +
                "\nKategori: " + category() +
                "\nPris: " + price + " kr" +
                "\nBeskrivning: " + description +
                "\nArtikelnummer: " + articleNumber;
    }

    public String toFileString() {
        return category() + ";" + title + ";" + price + ";" + description + ";" + articleNumber;
    }
}

