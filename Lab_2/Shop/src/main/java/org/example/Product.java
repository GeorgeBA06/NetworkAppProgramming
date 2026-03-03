package org.example;

import java.io.Serializable;

public abstract class Product implements Serializable {
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

    @Override
    public String toString(){
        return String.format("%s (категория: %s, цена: %d, количество: %d)",
                name,
                category.getDisplayName(),
                price,
                quantity
        );
    }


}
