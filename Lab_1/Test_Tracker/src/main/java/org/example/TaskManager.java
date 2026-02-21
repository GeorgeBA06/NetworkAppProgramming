package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static Logger logger = LogManager.getLogger(TaskManager.class);
    private List<Task> taskList;
    private final Scanner scanner;

    TaskManager(){
        this.taskList = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        initializeQuestions();
    }

    public void initializeQuestions(){
        logger.info("Initializing test questions");

        // Создаем тест по истории
        Task historyTask = new Task("История");

        List<String> answers1 = new ArrayList<>();
        answers1.add("1812 год");
        answers1.add("1917 год");
        answers1.add("1941 год");
        answers1.add("1991 год");
        historyTask.addQuestion(new Question(
                "В каком году началась Великая Отечественная война?",
                answers1,
                2, // правильный ответ: 1941 год (индекс 2)
                10
        ));

        List<String> answers2 = new ArrayList<>();
        answers2.add("Петр I");
        answers2.add("Екатерина II");
        answers2.add("Иван Грозный");
        answers2.add("Александр II");
        historyTask.addQuestion(new Question(
                "Кто основал Санкт-Петербург?",
                answers2,
                0, // правильный ответ: Петр I (индекс 0)
                15
        ));

        List<String> answers3 = new ArrayList<>();
        answers3.add("1240 год");
        answers3.add("1380 год");
        answers3.add("1480 год");
        answers3.add("1612 год");
        historyTask.addQuestion(new Question(
                "В каком году произошло Ледовое побоище?",
                answers3,
                0, // правильный ответ: 1240 год (индекс 0)
                15
        ));

        taskList.add(historyTask);

        // Создаем тест по географии
        Task geographyTask = new Task("География");

        List<String> answers4 = new ArrayList<>();
        answers4.add("Москва");
        answers4.add("Санкт-Петербург");
        answers4.add("Новосибирск");
        answers4.add("Казань");
        geographyTask.addQuestion(new Question(
                "Какая столица России?",
                answers4,
                0, // правильный ответ: Москва (индекс 0)
                10
        ));

        List<String> answers5 = new ArrayList<>();
        answers5.add("Амазонка");
        answers5.add("Нил");
        answers5.add("Волга");
        answers5.add("Миссисипи");
        geographyTask.addQuestion(new Question(
                "Какая река является самой длинной в мире?",
                answers5,
                1, // правильный ответ: Нил (индекс 1)
                20
        ));

        List<String> answers6 = new ArrayList<>();
        answers6.add("Россия");
        answers6.add("Канада");
        answers6.add("Китай");
        answers6.add("США");
        geographyTask.addQuestion(new Question(
                "Какая страна имеет самую большую площадь?",
                answers6,
                0, // правильный ответ: Россия (индекс 0)
                15
        ));

        taskList.add(geographyTask);

        // Создаем тест по литературе
        Task literatureTask = new Task("Литература");

        List<String> answers7 = new ArrayList<>();
        answers7.add("Война и мир");
        answers7.add("Преступление и наказание");
        answers7.add("Евгений Онегин");
        answers7.add("Мертвые души");
        literatureTask.addQuestion(new Question(
                "Какое произведение написал Лев Толстой?",
                answers7,
                0, // правильный ответ: Война и мир (индекс 0)
                15
        ));

        List<String> answers8 = new ArrayList<>();
        answers8.add("Александр Пушкин");
        answers8.add("Михаил Лермонтов");
        answers8.add("Николай Гоголь");
        answers8.add("Федор Достоевский");
        literatureTask.addQuestion(new Question(
                "Кто написал 'Евгения Онегина'?",
                answers8,
                0, // правильный ответ: Александр Пушкин (индекс 0)
                15
        ));

        taskList.add(literatureTask);

        logger.info("Successfully initialized " + taskList.size() + " tests");
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void runTest(){
        System.out.println("Система тестов");
        logger.info("Test system starts");

        if(taskList.isEmpty()){
            System.out.println("Нету подходящих тестов");
            logger.warn("There are no tests");
            return;
        }

        System.out.println("Доступные тесты:");
        for (int i = 0; i < taskList.size(); i++){
            System.out.println((i+1) + ". " + taskList.get(i).getTaskName()
                    + " (" + taskList.get(i).getQuestions().size()
                    + " вопросов)");
        }

        System.out.println("Выберете номер теста, который вы хотите пройти");
        int choice;
        try{
            choice = Integer.parseInt(scanner.nextLine()) - 1;
            if(choice < 0 || choice >= taskList.size()) {
                System.out.println("Вы ввели некорректное число!");
                return;
            }
        }catch (Exception ex){
            logger.error("The entered character is not a number!");
            System.out.println("Введенный символ не является числом!");
            return;
        }

        Task selectedTask = taskList.get(choice);
        takeTask(selectedTask);

    }

    private void takeTask(Task task){
        System.out.println("Тест по теме: " + task.getTaskName());
        System.out.println("Всего вопросов: " + task.getQuestions().size());
        System.out.println("Максимальный балл: " + task.getTotalScore());
        logger.info("User started doing test on the topic: " + task.getTaskName() );

        task.resetUserScore();
        List<Question> questions = task.getQuestions();

        for(int i = 0; i < questions.size(); i++){
            Question q = questions.get(i);

            System.out.println("Вопрос номер: " + (i+1) + " (баллов: " + q.getPoints() + ")");
            System.out.println(q.toString());

            System.out.println("Введите ваш вариант ответа: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if(q.checkAnswer(choice-1)){
                logger.info("User choose right answer number: " + choice + ". User gets " + q.getPoints() + " points");
                System.out.println("Верно " + q.getPoints() + " баллов");
                task.addToUserScore(q.getPoints());
            }else{
                logger.warn("User choose incorrect answer: " + choice + ". Correct answer: " + (q.getRightAnswer()+1));
                System.out.println("Неверно. Правильный ответ: " + (q.getRightAnswer() + 1));
            }

        }

        showResults(task);

    }

    public void showResults(Task task){
        System.out.println("Результаты теста: ");
        System.out.println("Тест: " + task.getTaskName());
        System.out.println("Пользователь набрал: " + task.getUserScore() + " баллов из " + task.getTotalScore());
        logger.info("User get " + task.getUserScore() + " from " + task.getTotalScore());

        double result = (double) task.getUserScore()/task.getTotalScore() * 100;
        System.out.println("Процент правильных ответов составляет: " + result + "%");

        if(result >= 85){
            System.out.println("Оценка отлично!");
            logger.info("User get excellent mark");
        }else if (result >= 70){
            System.out.println("Оценка хорошо!");
            logger.info("User get good mark");
        }else if (result >= 50){
            System.out.println("Оценка удовлетворительно!");
            logger.info("User get normal mark");
        }else {
            System.out.println("Оценка плохо!");
            logger.info("User get bad mark");
        }
    }

    public void showAllTasks(){
        System.out.println("Список всех тестов:");

        if(taskList.isEmpty()){
            System.out.println("Нет доступных тестов");
            return;
        }

        for(Task task : taskList){
            System.out.println("\n Тест на тему: " + task.getTaskName());
            System.out.println("Всего вопросов: " + task.getQuestions().size());
            System.out.println("Максимальное количество баллов за тест: " + task.getTotalScore());

            for(int i = 0; i < task.getQuestions().size(); i++){
                Question q  = task.getQuestions().get(i);

                System.out.println((i+1) + ". " + q.getQuestion() + " (баллов: " + q.getPoints() + ")");

            }
        }
    }

    public void close(){
        scanner.close();
    }

}
