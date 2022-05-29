package ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mil_app.R;

public class FalseAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_false_answer);

        //ramka pokazująca przegraną
        ImageView falseV = (ImageView) findViewById(R.id.looseView);
        falseV.setImageResource(R.drawable.loose1);

        // przycisk przenoszący do menu
        Button returnMenuB = (Button) findViewById(R.id.menuButton2);

        returnMenuB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }
        });
    }

    // ----- METHODS ------

    // przenoszenie do menu aplikacji
    private void openMenuActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}