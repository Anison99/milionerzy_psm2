package com.example.mil_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CorrectAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answer);

        //ramka pokazująca wygraną
        ImageView correctV = (ImageView) findViewById(R.id.winView);
        correctV.setImageResource(R.drawable.win1);

        // przycisk przenoszący do kolejnego pytania
        ImageButton nextIB = (ImageButton) findViewById(R.id.nextButton);

        nextIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuestions();
            }
        });
    }

    private void openQuestions() {
        Intent intent = new Intent(this, QuestionsActivity.class);
        startActivity(intent);
    }
}