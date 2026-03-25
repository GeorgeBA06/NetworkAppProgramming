package org.example;

public class Electronic extends Product implements Discountable{

    private int guaranteeTime;
    private double discount;

    Electronic(String name, int price, Category category, int quantity, int guaranteeTime, double discount){
        super(name, price, category, quantity);
        this.guaranteeTime = guaranteeTime;
        this.discount = discount;
    }

    public int getGuaranteeTime() {
        return guaranteeTime;
    }

    public void setGuaranteeTime(int guaranteeTime) {
        this.guaranteeTime = guaranteeTime;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        double discPrice = getDiscountedPrice(getPrice());
        return super.toString() + String.format(", гарантия %d дн., скидка: %.0f%%, цена со скидкой: %.2f",
                guaranteeTime, discount, discPrice);
    }

}
