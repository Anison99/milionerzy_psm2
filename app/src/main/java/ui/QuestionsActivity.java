package ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mil_app.R;
import com.example.mil_app.database.CSVController;
import com.example.mil_app.database.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsActivity extends AppCompatActivity {
	TextView questionT;
	TextView moneyL;
	TextView numberQestT;
	TextView hint;
	
	Button answer1B;
	Button answer2B;
	Button answer3B;
	Button answer4B;
	Button help1B; // tel do przyjaciela
	Button help2B; // publicznosc
	Button help3B; // 50/50
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);
		
		// ramka z pytaniami
		ImageView questionFrameV = (ImageView) findViewById(R.id.questionImage);
		questionFrameV.setImageResource(R.drawable.question_frame);
		
		// labele z numerem pytań oraz kwotą do wygrania
		TextView numberQestT = (TextView) findViewById(R.id.questionNumberTextView);
		TextView moneyL = (TextView) findViewById(R.id.currentPrizeTextView);
		// pytanie
		questionT = (TextView) findViewById(R.id.questionStringTextView);
		
		// przyciski z odpowiedziami
		Button answer1B = (Button) findViewById(R.id.answer1Button);
		Button answer2B = (Button) findViewById(R.id.answer2Button);
		Button answer3B = (Button) findViewById(R.id.answer3Button);
		Button answer4B = (Button) findViewById(R.id.answer4Button);
		
		//przyciski na koła ratunkowe
		Button help1B = (Button) findViewById(R.id.callFriendButton);
		Button help2B = (Button) findViewById(R.id.askAudienceButton);
		Button help3B = (Button) findViewById(R.id.fiftyFiftyButton);
		//	TextView hint = (TextView) findViewById(R.id.hintTextLabel); //<------------
		
		// przycisk sprawdzający
		Button checkB = (Button) findViewById(R.id.confirmButton);
		
		// przycisk uruchamiający nastepne activity
		checkB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//			chceckAnswer();                        //<------------
			}
		});
	}
	
	private static Question currentQuestion = null;
	private Boolean isCorrectAnswerSelected = null;
	
	public static Question getCurrentQuestion() {
		return currentQuestion;
	}
	
	// pobieranie danych z csv i umieszczenie w miejscu txt i przycisków
	private void setUpQuestion() {
		if (currentQuestion == null) {
			currentQuestion = CSVController.getRandomQuestion(1);
		} else {
			currentQuestion = CSVController
					.getRandomQuestion(currentQuestion.getQuestionNumber() + 1);
		}
		this.questionT.setText(currentQuestion.getQuestionString());
		this.numberQestT.setText(currentQuestion.getQuestionNumber() + " ");
		this.answer1B.setText(currentQuestion.getAnswer(Question.Answer.FIRST));
		this.answer2B.setText(currentQuestion.getAnswer(Question.Answer.SECOND));
		this.answer3B.setText(currentQuestion.getAnswer(Question.Answer.THIRD));
		this.answer4B.setText(currentQuestion.getAnswer(Question.Answer.FOURTH));
		this.moneyL.setText(currentQuestion.getPrize() + "$");
		resetButtonsColor();
		this.answer1B.setVisibility(View.INVISIBLE);
		this.answer2B.setVisibility(View.INVISIBLE);
		this.answer3B.setVisibility(View.INVISIBLE);
		this.answer4B.setVisibility(View.INVISIBLE);
		isCorrectAnswerSelected = null;
	}
	
	// zmiana koloru po kliknieciu
	private void resetButtonsColor() {
		answer1B.setBackgroundColor(Color.GREEN);
		answer2B.setBackgroundColor(Color.GREEN);
		answer3B.setBackgroundColor(Color.GREEN);
		answer4B.setBackgroundColor(Color.GREEN);
	}
	
	private void answerButtonSelected(View view) {
		Button selectedButton = (Button) findViewById(view.getId());
		resetButtonsColor();
		selectedButton.setBackgroundColor(Color.GREEN);
	/*	this.isCorrectAnswerSelected = switch (currentQuestion.getCorrect()) {
			case 1 -> selectedButton == answer1B;
			case 2 -> selectedButton == answer1B;
			case 3 -> selectedButton == answer1B;
			case 4 -> selectedButton == answer1B;
			default -> false;
		};
	 */
	}
	
	
	// metoda sprawdzająca które activity uruchomić (dobra/zła odpowiedź)
	private void checkAnswer(View view) {
		
		if (isCorrectAnswerSelected == null) {
			return;
		} else if (isCorrectAnswerSelected) {
			if (currentQuestion.getPrize() != 1000000) {
				setUpQuestion();
				return;
			}
			//root = FXMLLoader.load(getClass().getResource("summary.fxml"));
			Intent intent = new Intent(this, CorrectAnswerActivity.class); // -- okno wygrania miliona
			startActivity(intent);
		} else {
			//root = FXMLLoader.load(getClass().getResource("loose.fxml"));
			Intent intent = new Intent(this, FalseAnswerActivity.class); // -- okno przegranego
			startActivity(intent);
			
		}
		
		Intent intent = new Intent(this, FalseAnswerActivity.class);
		startActivity(intent);
	}
	
	public void fireFiftyFiftyButton(View view) {
		this.help3B.setVisibility(View.INVISIBLE);
		switch (currentQuestion.getCorrect()) {
			case 1:
			case 4:
				this.answer2B.setVisibility(View.INVISIBLE);
				this.answer3B.setVisibility(View.INVISIBLE);
				return;
			case 2:
			case 3:
				this.answer1B.setVisibility(View.INVISIBLE);
				this.answer4B.setVisibility(View.INVISIBLE);
		}
	}
	
	@RequiresApi(api = Build.VERSION_CODES.N)
	public void fireAskAudienceButton(View view) {
		this.help2B.setVisibility(View.INVISIBLE);
		int min = 50;
		List<Integer> results = new ArrayList<>();
		Random rand = new Random();
		int max = 100;
		byte iter = 0;
		boolean test = false;
		int temp = 0;
		do {
			if (currentQuestion.getAnswersArray()[iter] == currentQuestion
					.getAnswersArray()[currentQuestion.getCorrect() - 1]) {
				temp = rand.ints(min, max).findFirst().getAsInt();
				max = max - temp;
				min = 0;
				test = true;
				results.add(temp);
			}
			if (test) {
				temp = rand.ints(min, max).findFirst().getAsInt();
				max = max - temp;
				results.add(temp);
			} else {
				iter++;
			}
		} while (results.size() != 4);
		
		switch (currentQuestion.getCorrect()) {
			case 1:
				this.answer1B.setText(this.answer1B.getText() + " " + results.get(0));
				this.answer2B.setText(this.answer2B.getText() + " " + results.get(0));
				this.answer3B.setText(this.answer3B.getText() + " " + results.get(0));
				this.answer4B.setText(this.answer4B.getText() + " " + results.get(0));
				return;
			case 2:
				this.answer2B.setText(this.answer1B.getText() + " " + results.get(0));
				this.answer1B.setText(this.answer2B.getText() + " " + results.get(0));
				this.answer3B.setText(this.answer3B.getText() + " " + results.get(0));
				this.answer4B.setText(this.answer4B.getText() + " " + results.get(0));
				return;
			case 3:
				this.answer3B.setText(this.answer1B.getText() + " " + results.get(0));
				this.answer2B.setText(this.answer2B.getText() + " " + results.get(0));
				this.answer1B.setText(this.answer3B.getText() + " " + results.get(2));
				this.answer4B.setText(this.answer4B.getText() + " " + results.get(3));
				return;
			case 4:
				this.answer4B.setText(this.answer1B.getText() + " " + results.get(0));
				this.answer2B.setText(this.answer2B.getText() + " " + results.get(1));
				this.answer1B.setText(this.answer3B.getText() + " " + results.get(2));
				this.answer3B.setText(this.answer4B.getText() + " " + results.get(3));
		}
	}
	
	public void fireCallFriendButton(View view) {
		this.help1B.setVisibility(View.INVISIBLE);
		Random rand = new Random();
		// Siema tu jak masz numer pytania mniejszy niż 5 to zawsze dobra odpowiedź jest
		// zwracana
		if (currentQuestion.getPrize() < 20000) {
			this.hint
					.setText(currentQuestion.getAnswersArray()[currentQuestion.getCorrect() - 1]);
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				this.hint.setText(
						currentQuestion.getAnswersArray()[rand.ints(0, 3).findFirst().getAsInt()]);
			}
		}
	}
	
}