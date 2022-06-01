package ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mil_app.R;
import com.google.android.material.textfield.TextInputEditText;

public class NicknameActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nickname);
		
		// logo
		ImageView logo2V = (ImageView) findViewById(R.id.logoView);
		logo2V.setImageResource(R.drawable.milionerzy_logo);
		
		// przycisk png
		ImageView buttonV = (ImageView) findViewById(R.id.buttonImage);
		buttonV.setImageResource(R.drawable.button_style);
		
		// wczytywanie nazwy użytkownika
		TextInputEditText nicknameI = (TextInputEditText) findViewById(R.id.nicknameInput);
		String userNickname = nicknameI.getText().toString();
		
		// przycisk
		Button start2B = (Button) findViewById(R.id.start2Button);
		
		// przycisk uruchamiający nastepne activity
		start2B.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openQuestions();
			}
		});
	}
	
	// ----- METHODS ------
	
	// metoda pozwalająca uruchomić następne activity
	void openQuestions() {
		Intent intent = new Intent(this, QuestionsActivity.class);
		startActivity(intent);
	}
}