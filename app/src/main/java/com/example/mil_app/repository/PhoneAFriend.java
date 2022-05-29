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
        // Siema tu jak masz numer pytania mniejszy niż 5 to zawsze dobra odpowiedź jest zwracana
        if(lqNumber<5){
            return lCAnswer;
        }
        // A tu nie
        else{
            return lAnswers.get(rand.nextInt());
        }
        //TODO optimalization
    }
}
