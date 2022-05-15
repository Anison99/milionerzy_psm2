package com.example.mil_app.repository;

import java.util.List;
import java.util.Random;

public class AskTheAudience {
    int lqNumber;
    String lQuestion;
    List<String> lAnswers;
    String lCAnswer;
    List<Integer> results;
    public AskTheAudience(int qNumber,String question,List<String> answers,String cAnswer) {
        lqNumber = qNumber;
        lQuestion = question;
        lAnswers = answers;
        lCAnswer = cAnswer;
    }
    public List<Integer> getATAAlgorithmResult() {
        int min = 50;
        int max = 100;
        int result;
        int iter = 0;
        while (iter <= 3) {
            if (lAnswers.equals(lCAnswer)) {
                result = getRandomNumberUsingInts(min, max);
                max = max - result;
                min = min - result;
                results.add(result);
                iter++;
            } else {
                result = getRandomNumberUsingInts(min, max);
                max = max - result;
                min = min - result;
                results.add(result);
                iter++;
            }
        }
        return results;
    }
    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
