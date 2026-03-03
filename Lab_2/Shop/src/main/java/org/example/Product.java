package org.example;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int price;
    private final Category category;
    private int quantity;

    Product(String name, int price, Category category, int quantity){
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Товары на складе: \n");
//        return
//    }

    public void printPrettyString() {
        System.out.println("Название" + name);
    }
}
