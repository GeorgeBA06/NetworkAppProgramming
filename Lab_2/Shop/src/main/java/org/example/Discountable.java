package org.example;

public interface Discountable {
    double getDiscount();
    default double getDiscountedPrice(double originalPrice){
        return originalPrice * (100 - getDiscount()) / 100;
    }
}
