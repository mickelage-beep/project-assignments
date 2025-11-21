package se.jensen.mikael.webbshop.controller;

import se.jensen.mikael.webbshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }

    public List<Product> listProducts() {
        return products;
    }

    public List<Product> findProducts(String input) {
        List<Product> finds = new ArrayList<>();
        for (Product p : products){
            if (p.getArticleNumber().equalsIgnoreCase(input)){
                finds.add(p);
                break;
            }else
            if (p.getTitle().equalsIgnoreCase(input)){
                finds.add(p);

        }

        } return finds;
    }
}
