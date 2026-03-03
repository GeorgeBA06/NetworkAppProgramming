package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Shop {
    private static List<Product> productList = new ArrayList<>();

    public static void add(Product product){
        productList.add(product);
    }

    public static void addFood(String name, int price, Category category, int quantity,
                               LocalDate manufacturedDate, int shelfLifeDays) {
        Food food = new Food(name, price, category, quantity, manufacturedDate, shelfLifeDays);
        productList.add(food);
    }

    public static void addElectronics(String name,int price, Category category, int quantity,int guaranteeDays){
        Electronic electronic = new Electronic(name, price, category, quantity, guaranteeDays);
        productList.add(electronic);
    }

    public static void addClothes(String name,int price, Category category, int quantity, String brand){
        Clothes clothes = new Clothes(name,price,category,quantity,brand);
        productList.add(clothes);
    }



    public static List<Product> getProductList(){
        return productList;
    }

    public static void remove(Product product){
        productList.remove(product);
    }

    public static void changePrice(Product product, int newPrice){
        product.setPrice(newPrice);
    }

    public static List<Product> findProductsByName(String productsName){
       return productList.stream()
                .filter(product -> product.getName().equalsIgnoreCase(productsName))
                .collect(Collectors.toList());
    }

    public static Optional<Product> findFirstProductByName(String productName){
        return productList.stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst();
    }

    public static List<Product> filterByPrice(int price){
        return productList.stream()
                .filter(product -> product.getPrice() == price)
                .collect(Collectors.toList());
    }

    public static List<Product> filterByRangePrice(int minValue, int maxValue){
        return productList.stream()
                .filter(product -> product.getPrice() > minValue && product.getPrice() < maxValue)
                .collect(Collectors.toList());
    }

    public static void showStatistics(){
        long clothesCount = productList.stream().filter(product -> product instanceof Clothes).count();
        long foodCount = productList.stream().filter(product -> product instanceof Food).count();
        long electronicsCount = productList.stream().filter(product -> product instanceof Electronic).count();

        long allItemQuantity = productList.stream().mapToInt(Product::getQuantity).count();

        double averagePriceInShop = productList.stream().mapToInt(Product::getPrice).average().orElse(0);

        System.out.println("Одежды в наличии(предметов): " + clothesCount);
        System.out.println("Общее количество продуктов питания в наличии: " + foodCount);
        System.out.println("Общее количество электронных приборов: " + electronicsCount);
        System.out.println("Общее количество предметов в магазине: " + allItemQuantity);
        System.out.println("Средняя стоимость товаров в магазине: " + averagePriceInShop);
    }

    public static void sortByProductName(){
        productList.sort(Comparator.comparing(Product::getName));
    }

    public static void sortByProductPrice(){
        productList.sort(Comparator.comparing(Product::getPrice));
    }

    public static void categoryFilter(int choice){
        switch (choice){
            case 1 ->{
                System.out.println("Вы выбрали категорию продукты питания:");
                List<Product> foodList = productList.stream().filter(product -> product instanceof Food).toList();
                for(Product product : foodList){
                    System.out.println("1. " + product.getName() + " стоимость: " + product.getPrice() +
                            " количество единиц товара: " + product.getPrice());
                }
            }
            case 2 ->{
                System.out.println("Вы выбрали категорию одежда:");
                List<Product> foodList = productList.stream().filter(product -> product.getCategory().equals(Category.CLOTHES)).toList();
                for(Product product : foodList){
                    System.out.println("1. " + product.getName() + " стоимость: " + product.getPrice() +
                            " количество единиц товара: " + product.getPrice());
                }
            }
            case 3 ->{
                System.out.println("Вы выбрали категорию электроника:");
                List<Product> electronicList = productList.stream().filter(product -> product.getCategory().equals(Category.ELECTRONICS)).toList();
                for(Product product : electronicList){
                    System.out.println("1. " + product.getName() + " стоимость: " + product.getPrice() +
                            " количество единиц товара: " + product.getPrice());
                }
            }

        };
    }

    public static void sortByProductCategory(){
        productList.sort(Comparator.comparing(Product::getCategory));
    }
}
