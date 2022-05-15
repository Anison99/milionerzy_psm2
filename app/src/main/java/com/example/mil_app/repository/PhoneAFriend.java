package com.example.mil_app.repository;

import java.util.List;
import java.util.Random;

public class PhoneAFriend {
    int lqNumber;
    String lQuestion;
    List<String> lAnswers;
    String lCAnswer;
    public PhoneAFriend(int qNumber,String question,List<String> answers,String cAnswer){
        lqNumber = qNumber;
        lQuestion = question;
        lAnswers = answers;
        lCAnswer = cAnswer;
    }
    public String getPAFAnswer(){
        Random rand = new Random();
        if(lqNumber<5){
            return lCAnswer;
        }
        else{
            String s = lAnswers.get(rand.nextInt());
            System.out.print(lAnswers);
            return s;

        }
        //TODO optimalization
    }
}
