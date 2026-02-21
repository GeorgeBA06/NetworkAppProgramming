package org.example;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String taskName;
    private List<Question> questions = new ArrayList<>();
    private int userScore;
    private int totalScore;

    Task(String taskName){
        this.taskName = taskName;
        this.questions = new ArrayList<>();
        this.totalScore = 0;
        this.userScore = 0;
    }

    public void addQuestion(Question question){
        questions.add(question);
        totalScore += question.getPoints();
    }

    public void addToUserScore(int points){
        userScore += points;
    }

    public void resetUserScore(){
        userScore = 0;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getUserScore() {
        return userScore;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
