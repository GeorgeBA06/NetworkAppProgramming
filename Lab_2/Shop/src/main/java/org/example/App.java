package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.example.ScannerManager.*;

public class App
{
    public static void main( String[] args )
    {
        ScannerManager scan = new ScannerManager(System.in);
while(true){
    System.out.println("""
            Выберете сценарий работы программы:
            1.  Добавить продукт питания
            2.  Добавить электронику
            3.  Добавить одежду
            4.  Просмотреть весь ассортимент
            5.  Удалить товар
            6.  Редактировать цену
            7.  Редактировать гарантию (электроника)
            8.  Установить скидку на товар (одежда/электроника)
            9.  Поиск по названию
            10. Фильтр по категории + цена + срок годности
            11. Фильтр по цене (точная)
            12. Фильтр по диапазону цен
            13. Сортировка по названию
            14. Сортировка по цене
            15. Статистика по категориям и топ-3
            16. Журнал изменения цен
            17. Сохранение данных
            18. Загрузка данных
            0.  Выход из программы
            """);

    switch (scan.scanRangeInt(0,18)){
        case 0 -> {
            return;
        }
        case 1 -> {
            System.out.println("Введите название продукта питания:");
            String name = scan.scanString();
            System.out.println("Введите стоимость за единицу товара:");
            int price = scan.scanInt();
            System.out.println("Введите дату изготовления");
            LocalDate manufacturedDate = scan.scanLocaleDate();
            System.out.println("Введите количество дней который товар будет годен для потребления:");
            int someDays = scan.scanInt();
            System.out.println("Введите количество товара, который вы хотите добавить");
            int quantity = scan.scanInt();
            Shop.add(new Food(name, price, Category.FOOD, quantity, manufacturedDate, someDays));


        }
        case 2 ->{
            System.out.println("Введите название прибора, который хотите добавить:");
            String name = scan.scanString();
            System.out.println("Введите стоимость за единицу товара:");
            int price = scan.scanInt();
            System.out.println("Введите гарантийный срок товара в днях:");
            int someDays = scan.scanInt();
            System.out.println("Введите количество товара, который вы хотите добавить");
            int quantity = scan.scanInt();
            Shop.add(new Electronic(name, price, Category.ELECTRONICS, quantity, someDays));

        }
        case 3 ->{
            System.out.println("Введите название элемента одежды, который хотите добавить:");
            String name = scan.scanString();
            System.out.println("Введите стоимость за единицу товара:");
            int price = scan.scanRangeInt(0, 10000);
            System.out.println("Введите количество товара, который вы хотите добавить");
            int quantity = scan.scanInt();
            System.out.println("Введите название бренда, выпустившего данную одежду");
            String brandName = scan.scanString();
            System.out.println("Введите размер (XS, S, M, L, XL, XXL):");
            String size = scan.scanString();
            System.out.println("Введите скидку в процентах (0-100):");
            Shop.add(new Clothes(name, price, Category.CLOTHES, quantity, brandName, size));
        }
        case 4 ->{
            List<Product> productList = Shop.getProductList();
            if(productList.isEmpty()){
                System.out.println("В магазине отсутствует товар");
            }else {
                productList.forEach(System.out::println);
            }
        }
        case 5 -> {
            System.out.println("Введите пароль!");
            String password = scan.scanString();
            if (AuthenticationManager.checkPass(password)) {
                List<Product> productList = Shop.getProductList();
                if (productList.isEmpty()) {
                    System.out.println("В магазине нет товаров для удаления.");
                    break;
                }
                System.out.println("Список товаров:");
                for (int i = 0; i < productList.size(); i++) {
                    System.out.println(i + ". " + productList.get(i).getName() + " категория " + productList.get(i).getCategory());
                }
                System.out.println("Введите номер товара, который хотите удалить:");
                int choice = scan.scanInt();
                if (choice >= 0 && choice < productList.size()) {
                    Shop.remove(productList.get(choice));
                } else {
                    System.out.println("Вы ввели неверный номер товара");
                }
            } else {
                System.out.println("Вы ввели неправильный пароль");
            }
        }
        case 6->{ System.out.println("Введите пароль!");
            String password = scan.scanString();
            if (AuthenticationManager.checkPass(password)) {
                System.out.println("Список товаров:");
                List<Product> productList = Shop.getProductList();
                if (productList.isEmpty()) {
                    System.out.println("В магазине нет товаров для изменения цены.");
                    break;
                }
                for (int i = 0; i < productList.size(); i++) {
                    System.out.println(i + ". " + productList.get(i).getName() + " категория " + productList.get(i).getCategory());
                }
                System.out.println("Введите номер товара, цену которого хотите изменить:");
                int choice = scan.scanRangeInt(0,productList.size() - 1);
                System.out.println("Введите новую цену товара");
                int newPrice = scan.scanRangeInt(0,10000);
                Shop.changePrice(productList.get(choice), newPrice);
            }else {
                System.out.println("Вы ввели неправильный пароль");
            }
        }

        case 7 -> {
            System.out.println("Введите пароль!");
            String password = scan.scanString();
            if (AuthenticationManager.checkPass(password)) {
            List<Product> productList = Shop.getProductList();
            if(productList.isEmpty()){
                System.out.println("В магазине отсутствует товар");
                break;
            }else {
                for(Product product : productList){
                    if(product instanceof Electronic electronic){
                        System.out.println("- " + product.getName() + " (гарантия: " + electronic.getGuaranteeTime() + " дн.)");
                    }
                }
            }
            System.out.println("Введите название товара, гарантию которого хотите изменить:");
            String productName = scan.scanString();
            Optional<Product> optionalProduct = Shop.findFirstProductByName(productName);
            if(optionalProduct.isPresent()){
                Product product = optionalProduct.get();

                if(product instanceof  Electronic electronic){
                    System.out.println("Введите новый гарантийный срок:");
                    int newGuarantee = scan.scanInt();
                    electronic.setGuaranteeTime(newGuarantee);
                    System.out.println("Гарантия успешно обновлена!");
                }else {
                    System.out.println("Ошибка: товар " + productName + " не является электроникой");
                }
            }else {
                System.out.println("Ошибка: товар с названием " + productName + " не найден");
            }
            }
        }
        case 8 -> {
            System.out.println("Введите пароль:");
            String password = scan.scanString();
            if (AuthenticationManager.checkPass(password)) {
                System.out.println("Список товаров, поддерживающих скидку (одежда/электроника):");
                Shop.getProductList().stream()
                        .filter(p -> p instanceof Clothes || p instanceof Electronic)
                        .forEach(p -> System.out.println(p.getName() + " (" + p.getCategory() + ")"));
                System.out.println("Введите название товара:");
                String name = scan.scanString();
                Optional<Product> opt = Shop.findFirstProductByName(name);
                if (opt.isPresent()) {
                    System.out.println("Введите новую скидку (0-100):");
                    double discount = scan.scanRangeInt(0,100);
                    Shop.setDiscount(opt.get(), discount);
                } else {
                    System.out.println("Товар не найден.");
                }
            } else {
                System.out.println("Неверный пароль.");
            }
        }

        case 9 ->{
            System.out.println("Введите название товара, который хотите найти");
            String productName = scan.scanString();
            Shop.findProductsByName(productName).forEach(System.out::println);
        }
        case 10 -> {
            System.out.println("Выберите категорию (введите номер):\n1. Продукты питания\n2. Одежда\n3. Электроника\n0. Все категории");
            int catChoice = scan.scanRangeInt(0, 3);
            Category cat = switch (catChoice) {
                case 1 -> Category.FOOD;
                case 2 -> Category.CLOTHES;
                case 3 -> Category.ELECTRONICS;
                default -> null;
            };
            System.out.println("Введите минимальную цену (со скидкой):");
            int minPrice = scan.scanInt();
            System.out.println("Введите максимальную цену:");
            int maxPrice = scan.scanInt();if (minPrice > maxPrice) {
                int temp = minPrice;
                minPrice = maxPrice;
                maxPrice = temp;
                System.out.println("Минимальная цена больше максимальной, значения автоматически переставлены.");
            }
            System.out.println("Фильтр по сроку годности (1 – только просроченные, 2 – только свежие, 0 – без фильтра):");
            int expChoice = scan.scanRangeInt(0, 2);
            Boolean expiredStatus = switch (expChoice) {
                case 1 -> true;
                case 2 -> false;
                default -> null;
            };
            List<Product> filtered = Shop.filterProducts(cat, minPrice, maxPrice, expiredStatus);
            if (filtered.isEmpty()) {
                System.out.println("Товары не найдены.");
            } else {
                filtered.forEach(System.out::println);
            }
        }
        case 11->{
            System.out.println("Введите точную стоимость товара, который хотите найти:");
            int price = scan.scanInt();
            if(!Shop.getProductList().isEmpty()){
                Shop.filterByPrice(price).forEach(System.out::println);
            }else{
                System.out.println("В магазине отсутствуют товары!");
            }
        }
        case 12 ->{
            System.out.println("Введите минимальную стоимость товара, который хотите найти:");
            int minValue = scan.scanRangeInt(0, 10000);
            System.out.println("Введите максимальную стоимость товара, который хотите найти:");
            int maxValue = scan.scanRangeInt(0,10000);
            if (minValue > maxValue) {
                int temp = minValue;
                minValue = maxValue;
                maxValue = temp;
                System.out.println("Минимальная цена больше максимальной, значения автоматически переставлены.");
            }
            if(!Shop.getProductList().isEmpty()){
                Shop.filterByRangePrice(minValue,maxValue).forEach(System.out::println);
            }else{
                System.out.println("В магазине отсутствуют товары!");
            }
        }
        case 13 ->{
           Shop.sortByProductName();
        }
        case 14 ->{
            Shop.sortByProductPrice();
        }

        case 15 ->{
            Shop.showStatistics();
        }

        case 16 -> Shop.showPriceLog();
        case 17->{
            FileManager.saveToFile(Shop.getProductList());
        }
        case 18->{
            List<Product> loaded = FileManager.loadFromFile();
            if (!loaded.isEmpty()) {
                Shop.getProductList().clear();
                Shop.getProductList().addAll(loaded);
                Shop.refreshTop3();
                Shop.refreshExpirationMap();
            }
        }
    }
}

    }
}
