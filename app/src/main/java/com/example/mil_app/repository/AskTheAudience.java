package com.example.mil_app.repository;

import java.util.List;
import java.util.Random;

public class AskTheAudience {
    int number;
    String lQuestion;
    List<String> lAnswers;
    String lCAnswer;
    List<Integer> results;

    public AskTheAudience(int number, String question, List<String> answers, String cAnswer) {
        this.number = number;
        this.lQuestion = question;
        this.lAnswers = answers;
        this.lCAnswer = cAnswer;
    }

    public List<Integer> getATAAlgorithmResult() {
        int min = 50;
        int max = 100;
        int result;
        int iter = 0;
        while (iter <= 3) {
            if (lAnswers.equals(lCAnswer)) {
            }
            result = getRandomNumberUsingInts(min, max);
            max = max - result;
            min = min - result;
            results.add(result);
            iter++;
        }
        return results;
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }
}
