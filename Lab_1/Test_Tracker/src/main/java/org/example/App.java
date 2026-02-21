package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        Logger logger = LogManager.getLogger(App.class);
        logger.info("Program starts");
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Пройти тест");
            System.out.println("2. Показать все тесты");
            System.out.println("3. Выйти");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice){
                case "1" :
                    taskManager.runTest();
                    break;
                case "2" :
                    taskManager.showAllTasks();
                    break;
                case "3":
                    System.out.println("Конец работы");
                    taskManager.close();
                    scanner.close();
                    logger.info("Program ended");
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");

            }
        }
    }
}
