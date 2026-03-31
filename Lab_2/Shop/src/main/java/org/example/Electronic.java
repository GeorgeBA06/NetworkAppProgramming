package org.example;

public class Electronic extends Product implements Discountable {
    private int guaranteeTime;

    public Electronic(String name, int price, Category category, int quantity, int guaranteeTime) {
        super(name, price, category, quantity);
        this.guaranteeTime = guaranteeTime;
    }

    public int getGuaranteeTime() { return guaranteeTime; }
    public void setGuaranteeTime(int guaranteeTime) { this.guaranteeTime = guaranteeTime; }

    @Override
    public double getDiscount() {
        return Shop.getDiscount(this);
    }

    @Override
    public String toString() {
        double discPrice = getDiscountedPrice(getPrice());
        return super.toString() + String.format(", гарантия %d дн., скидка: %.0f%%, цена со скидкой: %.2f",
                guaranteeTime, getDiscount(), discPrice);
    }
}