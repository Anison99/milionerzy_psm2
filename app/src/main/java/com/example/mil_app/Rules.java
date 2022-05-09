package com.example.mil_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        // ramka z przedstawieniem zasad
        ImageView rulesV = (ImageView) findViewById(R.id.frame1);
        rulesV.setImageResource(R.drawable.rules_frame);

        // przycisk
        Button nextB = (Button) findViewById(R.id.agreeingButton);

        // przycisk uruchamiający nastepne activity
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRules();
            }
        });
    }

    // ----- METHODS ------

    // metoda pozwalająca uruchomić następne activity
    private void openRules() {
        Intent intent = new Intent(this, Nickname.class);
        startActivity(intent);
    }
}