package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

public class Shop {
    private static final Logger logger = LogManager.getLogger(Shop.class);
    private static List<Product> productList = new ArrayList<>();
    private static List<PriceChangeLogEntry> priceLog = new ArrayList<>();

    public static void add(Product product){

        productList.add(product);
        logger.info("Добавлен товар: {} (категория: {}, цена: {}, количество: {})",
                product.getName(), product.getCategory().getDisplayName(),
                product.getPrice(), product.getQuantity());

    }

    public static List<Product> getProductList(){
        return productList;
    }

    public static void remove(Product product){

        productList.remove(product);
        logger.warn("Удалён товар: {} (категория: {})", product.getName(), product.getCategory().getDisplayName());
    }

    public static void changePrice(Product product, int newPrice){
        int oldPrice = product.getPrice();
        priceLog.add(new PriceChangeLogEntry(product.getName(), product.getPrice(), newPrice));
        product.setPrice(newPrice);
        logger.info("Изменена цена товара: {} с {} на {}", product.getName(), oldPrice, newPrice);
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
        List<Product> filtered =  productList.stream()
                .filter(product -> product.getPrice() == price)
                .collect(Collectors.toList());
        logger.debug("Фильтр по точной цене {}: найдено {} товаров", price, filtered.size());
        return filtered;
    }

    public static List<Product> filterByRangePrice(int minValue, int maxValue){
        return productList.stream()
                .filter(product -> product.getPrice() > minValue && product.getPrice() < maxValue)
                .collect(Collectors.toList());
    }

    public static List<Product> filterProducts(Category category,
                                               int minPrice,
                                               int maxPrice,
                                               Boolean expiredStatus){
        return productList.stream()
                .filter(product -> category == null || product.getCategory() == category)
                .filter(product -> product.getEffectivePrice() >= minPrice && product.getEffectivePrice()<= maxPrice)
                .filter(product -> {
                    if(expiredStatus == null) return true;
                    if(!(product instanceof Expirable)) return false;
                    boolean expired = ((Expirable) product).isExpired();
                    return expiredStatus == expired;
                })
                .collect(Collectors.toList());
    }

    public static List<Product> getTop3Expensive(){
        return productList.stream()
                .sorted(Comparator.comparingDouble(Product::getEffectivePrice).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }



    public static void showStatistics(){
        long clothesCount = productList.stream().filter(product -> product instanceof Clothes).count();
        long foodCount = productList.stream().filter(product -> product instanceof Food).count();
        long electronicsCount = productList.stream().filter(product -> product instanceof Electronic).count();

        long allItemQuantity = productList.stream().mapToInt(Product::getQuantity).sum();

        double averagePriceInShop = productList.stream().mapToInt(Product::getPrice).average().orElse(0);

        System.out.println("Одежды в наличии(предметов): " + clothesCount);
        System.out.println("Общее количество продуктов питания в наличии: " + foodCount);
        System.out.println("Общее количество электронных приборов: " + electronicsCount);
        System.out.println("Общее количество предметов в магазине: " + allItemQuantity);
        System.out.println("Средняя стоимость товаров в магазине: " + averagePriceInShop);

        System.out.println("\nТоп-3 самых дорогих товара (со скидками):");
        List<Product> top3 = getTop3Expensive();
        if (top3.isEmpty()) {
            System.out.println("Нет товаров.");
        } else {
            for (int i = 0; i < top3.size(); i++) {
                Product p = top3.get(i);
                System.out.printf("%d. %s – %.2f%n", i+1, p.getName(), p.getEffectivePrice());
            }
        }
    }

    public static void sortByProductName(){
        productList.sort(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));
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
                    System.out.println(product.getName() + " стоимость: " + product.getPrice() +
                            " количество единиц товара: " + product.getQuantity());
                }
            }
            case 2 ->{
                System.out.println("Вы выбрали категорию одежда:");
                List<Product> foodList = productList.stream().filter(product -> product.getCategory().equals(Category.CLOTHES)).toList();
                for(Product product : foodList){
                    System.out.println(product.getName() + " стоимость: " + product.getPrice() +
                            " количество единиц товара: " + product.getQuantity());
                }
            }
            case 3 ->{
                System.out.println("Вы выбрали категорию электроника:");
                List<Product> electronicList = productList.stream().filter(product -> product.getCategory().equals(Category.ELECTRONICS)).toList();
                for(Product product : electronicList){
                    System.out.println(product.getName() + " стоимость: " + product.getPrice() +
                            " количество единиц товара: " + product.getQuantity());
                }
            }

        };
    }

    public static void setDiscount(Product product, double discount){
        if(product instanceof Discountable){
            if(product instanceof Clothes){
                ((Clothes) product).setDiscount(discount);
            }else if (product instanceof Electronic){
                ((Electronic) product).setDiscount(discount);
            }else{
                System.out.println("Товар не поддерживает скидку(продукты питания имеют автоматическую скидку по сроку годности)");
            }
        }else {
            System.out.println("Товар не поддерживает скидку.");
        }
    }

    public static void showPriceLog(){
        if(priceLog.isEmpty()){
            System.out.println("Журнал изменений цен пуст.");
        }else{
            priceLog.forEach(System.out::println);
        }
    }

    public static void sortByProductCategory(){
        productList.sort(Comparator.comparing(Product::getCategory));
    }
}
