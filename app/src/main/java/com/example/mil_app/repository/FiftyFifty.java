package com.example.mil_app.repository;

import java.util.List;
import java.util.Random;

public class FiftyFifty {
    private List<String> lAnswers;
    private String lCAnswer;
    private List<String> newLAnswers;
    public FiftyFifty(List<String> answers,byte cAnswer){
        lAnswers = answers;
        lCAnswer = String.valueOf(cAnswer);
    }
    public List<String> getlAnswers(){
        newLAnswers.add(lCAnswer);
        Random rand =new Random();
        String temp = lAnswers.get(rand.nextInt());
        newLAnswers.add(temp);
        return newLAnswers;
    }
    //TODO optimalization
}
