package org.example;

public class Clothes extends Product{

    private String brand;

    Clothes(String name, int price, Category category, int quantity, String brand){
        super(name, price, category, quantity);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(
                " , бренд: %s", brand
        );
    }

}
