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
            1. Добавить продукт питания
            2. Добавить электронику
            3. Добавить одежду
            4. Просмотреть весь ассортимент
            5. Удалить товар
            6. Редактировать цену
            7. Редактировать гарантию
            8. Поиск по названию
            9. Фильтр по категории
            10. Фильтр по цене
            11. Фильтр по диапазону цен
            12. Сортировка по названию
            13. Сортировка по цене
            14. Статистика по категориям
            15. Сохранение данных
            16. Загрузка данных
            0. Выход из программы
            """);

    switch (scan.scanInt()){
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
            int price = scan.scanInt();
            System.out.println("Введите количество товара, который вы хотите добавить");
            int quantity = scan.scanInt();
            System.out.println("Введите название бренда, выпустившего данную одежду");
            String brandName = scan.scanString();
            Shop.add(new Clothes(name, price, Category.CLOTHES, quantity, brandName));
        }
        case 4 ->{
            List<Product> productList = Shop.getProductList();
            if(productList.isEmpty()){
                System.out.println("В магазине отсутствует товар");
            }else {
                productList.forEach(System.out::println);
            }
        }
        case 5 ->{
            System.out.println("Введите пароль!");
            String password = scan.scanString();
            if (AuthenticationManager.checkPass(password)) {
                System.out.println("Список товаров:");
                List<Product> productList = Shop.getProductList();
                for (int i = 0; i < productList.size(); i++){
                    System.out.println(i + ". " + productList.get(i).getName() + " категория " + productList.get(i).getCategory() );
                }
                System.out.println("Введите номер товара, который хотите удалить:");
                int choice = scan.scanInt();
                if(choice >= 0 && choice <= productList.size()){
                    Shop.remove(productList.get(choice));
                }else{
                    System.out.println("Вы ввели неверный номер товара");
                }

            }else {
                System.out.println("Вы ввели неправильный пароль");
            }

        }
        case 6->{ System.out.println("Введите пароль!");
            String password = scan.scanString();
            if (AuthenticationManager.checkPass(password)) {
                System.out.println("Список товаров:");
                List<Product> productList = Shop.getProductList();
                for (int i = 0; i < productList.size(); i++) {
                    System.out.println(i + ". " + productList.get(i).getName() + " категория " + productList.get(i).getCategory());
                }
                System.out.println("Введите номер товара, цену которого хотите изменить:");
                int choice = scan.scanInt();
                System.out.println("Введите новую цену товара");
                int newPrice = scan.scanInt();
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

        case 8 ->{
            System.out.println("Введите название товара, который хотите найти");
            String productName = scan.scanString();
            Shop.findProductsByName(productName).forEach(System.out::println);
        }
        case 9 ->{
            System.out.println("""
                    Выберете номер категории, которую хотите просмотреть
                    1. Продукты питания
                    2. Одежда
                    3. Электроника
                    """);
            int choice = scan.scanRangeInt(1, 3);
            Shop.categoryFilter(choice);

        }
        case 10->{
            System.out.println("Введите точную стоимость товара, который хотите найти:");
            int price = scan.scanInt();
            if(!Shop.getProductList().isEmpty()){
                Shop.filterByPrice(price).forEach(System.out::println);
            }else{
                System.out.println("В магазине отсутствуют товары!");
            }
        }
        case 11 ->{
            System.out.println("Введите минимальную стоимость товара, который хотите найти:");
            int minValue = scan.scanInt();
            System.out.println("Введите максимальную стоимость товара, который хотите найти:");
            int maxValue = scan.scanInt();
            if(!Shop.getProductList().isEmpty()){
                Shop.filterByRangePrice(minValue,maxValue).forEach(System.out::println);
            }else{
                System.out.println("В магазине отсутствуют товары!");
            }
        }
        case 12 ->{
           Shop.sortByProductName();
        }
        case 13 ->{
            Shop.sortByProductPrice();
        }

        case 14 ->{
            Shop.showStatistics();
        }
        case 15->{
            FileManager.saveToFile(Shop.getProductList());
        }
        case 16->{
            List<Product> loaded = FileManager.loadFromFile();
            if (!loaded.isEmpty()) {
                Shop.getProductList().clear();
                Shop.getProductList().addAll(loaded);
            }
        }
    }
}

    }
}
