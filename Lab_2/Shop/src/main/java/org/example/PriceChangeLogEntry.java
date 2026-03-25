package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceChangeLogEntry {
    private final String productName;
    private final int oldPrice;
    private final int newPrice;
    private final LocalDateTime timestamp;

    public PriceChangeLogEntry(String productName, int oldPrice, int newPrice) {
        this.productName = productName;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return String.format("[%s] %s: %d -> %d", timestamp.format(formatter), productName, oldPrice, newPrice);
    }

}
