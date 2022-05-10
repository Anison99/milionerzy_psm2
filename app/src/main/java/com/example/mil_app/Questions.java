package com.example.mil_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // ramka z pytaniami
        ImageView questionFrameV = (ImageView) findViewById(R.id.questionImage);
        questionFrameV.setImageResource(R.drawable.question_frame);

        // labele z numerem pytań oraz kwotą do wygrania
        TextView numberQestT = (TextView)findViewById(R.id.questionNumberLabel);
        TextView moneyL = (TextView)findViewById(R.id.moneyLabel);
        // pytanie
        TextView questionT = (TextView)findViewById(R.id.questionText);

        // przyciski z odpowiedziami
        Button answer1B = (Button) findViewById(R.id.answer1Button);
        Button answer2B = (Button) findViewById(R.id.answer2Button);
        Button answer3B = (Button) findViewById(R.id.answer3Button);
        Button answer4B = (Button) findViewById(R.id.answer4Button);

        //przyciski na koła ratunkowe
        Button help1B = (Button) findViewById(R.id.help1Button);
        Button help2B = (Button) findViewById(R.id.help2Button);
        Button help3B = (Button) findViewById(R.id.help3Button);

        // przycisk sprawdzający
        Button checkB = (Button) findViewById(R.id.checkButton);

        // przycisk uruchamiający nastepne activity
        checkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chceckAnswer();
            }
        });
    }
    // ----- METHODS ------

    // metoda sprawdzająca które activity uruchomić (dobra/zła odpowiedź)
    private void chceckAnswer() {

        // -------------- testowanie okien z poprawną/ złą odpowiedzią --------------
       /* Intent intent = new Intent(this, CorrectAnswer.class);
        startActivity(intent);
        */

        Intent intent = new Intent(this, FalseAnswer.class);
        startActivity(intent);

        // ------ DO UZUPEŁNIENIA JAK BĘDZIE BAZA DANYCH Z ODPOWIEDZIAMI ------

      /*  if ([zmienna_poprawna_odpowiedz] == TRUE){
            Intent intent = new Intent(this, CorrectAnswer.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, FalseAnswer.class);
            startActivity(intent);
        }
       */
    }
}
