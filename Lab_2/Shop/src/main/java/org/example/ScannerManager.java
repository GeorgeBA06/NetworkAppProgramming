package org.example;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

public class ScannerManager {
   public final Scanner scanner;

   public ScannerManager(InputStream inputStream) {
       scanner = new Scanner(inputStream);
   }

   public int scanInt(){
       return scan(scanner :: nextInt);
   }


   public int scanRangeInt(int minValue, int maxValue){
       while(true){

               int scanInt = scanInt();
               if(minValue <= scanInt && maxValue >= scanInt){
                  return scanInt;
               }
               else {
                   System.out.println("Вы введи число не входящее в диапазон. Повторите попытку");
               }
       }
   }

   public String scanString(){
       return scan(scanner :: next);
   }

   public LocalDate scanLocaleDate(){
       while(true){
           String dateString = scanString();
           try{
               return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
           }catch(DateTimeParseException ex){
               System.out.println("Неправильный формат. Повторите попытку!");
           }
       }

   }

   public <T> T scan(Supplier<T> supplier){
       while (true){
           try{
               return supplier.get();
           }catch (InputMismatchException ex){
                System.out.println("Неправильный ввод. Попробуйте еще");
                scanner.next();
           }
       }
   }
}
