package ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.mil_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // logo
        ImageView logoV = (ImageView) findViewById(R.id.milLogo);
        logoV.setImageResource(R.drawable.milionerzy_logo);

        // przycisk start
        ImageButton startB = (ImageButton)findViewById(R.id.startButton);
        startB.setImageResource(R.drawable.start_button);

        // przycisk uruchamiający drugie activity
        startB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRules();
            }
        });
    }

    // ----- METHODS ------

    // metoda pozwalająca uruchomić następne activity
    private void openRules() {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }
}