package org.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Food extends Product{
    private LocalDate manufacturedDate;
    private LocalDate expiredDate;


    public Food(String name, int price, Category category, int quantity,  LocalDate manufacturedDate,
         int plusSomeDays){
        super(name, price, category, quantity);
        this.manufacturedDate = manufacturedDate;
        this.expiredDate = manufacturedDate.plusDays(plusSomeDays);

    }

    public LocalDate getManufacturedDate() {
        return manufacturedDate;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public boolean isExpired(){
        return LocalDate.now().isAfter(expiredDate);
    }

    public long daysUntilExpire(){
        return ChronoUnit.DAYS.between(LocalDate.now(),expiredDate);
    }

    public String getStatus(){
        long days = daysUntilExpire();
        if(days < 0){
            return  "ПРОСРОЧЕН";
        } else if (days <= 3) {
            return "КРИТИЧЕСКИЙ (осталось " + days  + " дн.)";
        } else if (days <= 7) {
            return "ВНИМАНИЕ  ( осталось " + days + " дн.)";
        }else {
            return "НОРМАЛЬНЫЙ";
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return super.toString() + String.format(", произведен: %s, годен до: %s, статус: %s",
                manufacturedDate.format(formatter),
                expiredDate.format(formatter),
                getStatus());
    }
}
