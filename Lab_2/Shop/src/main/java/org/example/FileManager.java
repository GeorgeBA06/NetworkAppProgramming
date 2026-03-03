package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "products.dat";

    public static void saveToFile(List<Product> productList){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(productList);
        }catch (IOException ex){
            System.out.println("Ошибка записи в файл");
        }
    }

    public static List<Product> loadFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Product>) ois.readObject();
        }catch (IOException | ClassNotFoundException ex){
            System.out.println("Ошибка чтения из файла");
            return new ArrayList<>();
        }
    }

}
