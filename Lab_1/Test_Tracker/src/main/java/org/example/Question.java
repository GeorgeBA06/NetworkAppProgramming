package org.example;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private List<String> answers = new ArrayList<>();
    private int rightAnswer;
    private int points;

    public Question(String question, List<String> answers, int rightAnswer, int points){
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswer() {
        return answers;
    }

    public void setAnswer(List<String> answer) {
        this.answers = answer;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean checkAnswer(int userAnswer){
        return rightAnswer == userAnswer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(question).append("\n");
        for(int i = 0; i < answers.size(); i++){
            sb.append(i+1).append(". ").append(answers.get(i)).append("\n");
        }
        return sb.toString();
    }
}
