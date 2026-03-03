package org.example;

public class Electronic extends Product{

    private int guaranteeTime;

    Electronic(String name, int price, Category category, int quantity, int guaranteeTime){
        super(name, price, category, quantity);
        this.guaranteeTime = guaranteeTime;
    }

    public int getGuaranteeTime() {
        return guaranteeTime;
    }

    public void setGuaranteeTime(int guaranteeTime) {
        this.guaranteeTime = guaranteeTime;
    }
}
