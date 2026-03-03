package org.example;

public enum Category {
    FOOD("Продукты питания", "Продовольственные товары"),
    CLOTHES("Одежда", "Текстильные изделия"),
    ELECTRONICS("Электроника", "Бытовая техника и электроника");

    private final String displayName;
    private final String description;

    Category(String displayName, String desription){
        this.displayName = displayName;
        this.description = desription;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Category fromDisplayName(String displayName){
        for(Category category : values()){
            if(category.displayName.equalsIgnoreCase(displayName)){
                return category;
            }
        }
        throw new IllegalArgumentException("Такая категория товаров отсутствует " + displayName);
    }
}
