package org.example;

public class Clothes extends Product implements Discountable {
    private String brand;
    private String size;

    public Clothes(String name, int price, Category category, int quantity, String brand, String size) {
        super(name, price, category, quantity);
        this.brand = brand;
        this.size = size;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    @Override
    public double getDiscount() {
        return Shop.getDiscount(this);
    }

    @Override
    public String toString() {
        double discPrice = getDiscountedPrice(getPrice());
        return super.toString() + String.format(", бренд: %s, размер: %s, скидка: %.0f%%, цена со скидкой: %.2f",
                brand, size, getDiscount(), discPrice);
    }
}