package me.chchnikolaou.unipiplishopping.order;

import com.google.firebase.Timestamp;

public class Order {

    private final String product;
    private final String name;
    private final double finalPrice;
    private final Timestamp purchasedDate;


    public Order(String product, String name, double finalPrice, Timestamp purchasedDate) {
        this.product = product;
        this.name = name;
        this.finalPrice = finalPrice;
        this.purchasedDate = purchasedDate;
    }


    public String getProduct() {
        return product;
    }

    public String getName() {
        return name;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public Timestamp getPurchasedDate() {
        return purchasedDate;
    }
}
