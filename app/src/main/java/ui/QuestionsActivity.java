package ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mil_app.R;
import com.example.mil_app.database.CSVController;
import com.example.mil_app.database.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsActivity extends AppCompatActivity {
	/**
	 * Pytanie
	 */
	TextView questionT = findViewById(R.id.questionStringTextView);
	/**
	 * Kwota do wygrania
	 */
	TextView moneyL = findViewById(R.id.currentPrizeTextView);
	/**
	 * Numer pytania
	 */
	TextView numberQestT = findViewById(R.id.questionNumberTextView);
	/**
	 * Podpowiedź?
	 */
	TextView helpText = findViewById(R.id.hintTextLabel);
	
	Button answer1Button = findViewById(R.id.answer1Button);
	Button answer2Button = findViewById(R.id.answer2Button);
	Button answer3Button = findViewById(R.id.answer3Button);
	Button answer4Button = findViewById(R.id.answer4Button);
	/**
	 * Telefon do przyjaciela
	 */
	Button callFriendButton = findViewById(R.id.callFriendButton);
	/**
	 * Pytanie do publiczności
	 */
	Button askAudienceButton = findViewById(R.id.askAudienceButton);
	/**
	 * Pół na pół. Odrzucanie dwóch losowych odpowiedzi
	 */
	Button fiftyFiftyButton = findViewById(R.id.fiftyFiftyButton);
	
	/**
	 * Zatwierdzanie odpowiedzi
	 */
	Button confirmButton = findViewById(R.id.confirmButton);
	private Boolean isCorrectAnswerSelected = null;
	private static Question currentQuestion = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);
		helpText.setText("");
	}
	
	// pobieranie danych z csv i umieszczenie w miejscu txt i przycisków
	@SuppressLint("SetTextI18n")
	private void setUpQuestion() {
		if (currentQuestion == null) {
			currentQuestion = CSVController.getRandomQuestion(1);
		} else {
			currentQuestion = CSVController
					.getRandomQuestion(currentQuestion.getQuestionNumber() + 1);
		}
		assert currentQuestion != null;
		this.questionT.setText(currentQuestion.getQuestionString());
		this.numberQestT.setText(currentQuestion.getQuestionNumber() + " ");
		this.answer1Button.setText(currentQuestion.getAnswer(Question.Answer.FIRST));
		this.answer2Button.setText(currentQuestion.getAnswer(Question.Answer.SECOND));
		this.answer3Button.setText(currentQuestion.getAnswer(Question.Answer.THIRD));
		this.answer4Button.setText(currentQuestion.getAnswer(Question.Answer.FOURTH));
		this.moneyL.setText(currentQuestion.getPrize() + "$");
		resetButtonsColor();
		this.answer1Button.setVisibility(View.INVISIBLE);
		this.answer2Button.setVisibility(View.INVISIBLE);
		this.answer3Button.setVisibility(View.INVISIBLE);
		this.answer4Button.setVisibility(View.INVISIBLE);
		isCorrectAnswerSelected = null;
	}
	
	// zmiana koloru po kliknieciu
	private void resetButtonsColor() {
		answer1Button.setBackgroundColor(Color.GREEN);
		answer2Button.setBackgroundColor(Color.GREEN);
		answer3Button.setBackgroundColor(Color.GREEN);
		answer4Button.setBackgroundColor(Color.GREEN);
	}
	
	public void answerButtonSelected(View view) {
		Button selectedButton = findViewById(view.getId());
		this.isCorrectAnswerSelected = switch (currentQuestion.getCorrect()) {
			case 1 -> selectedButton == answer1Button;
			case 2 -> selectedButton == answer2Button;
			case 3 -> selectedButton == answer3Button;
			case 4 -> selectedButton == answer4Button;
			default -> false;
		};
		resetButtonsColor();
		selectedButton.setBackgroundColor(Color.MAGENTA);
	}
	
	public void confirmAnswer(View view) {
		if (isCorrectAnswerSelected == null) {
			return;
		}
		if (isCorrectAnswerSelected) {
			if (currentQuestion.getPrize() != 1000000) {
				setUpQuestion();
				this.helpText.setText("");
			} else {
				startActivity(new Intent(this, CorrectAnswerActivity.class));
			}
		} else {
			startActivity(new Intent(this, FalseAnswerActivity.class));
		}
	}
	
	public void fireFiftyFiftyButton(View view) {
		this.fiftyFiftyButton.setVisibility(View.INVISIBLE);
		switch (currentQuestion.getCorrect()) {
			case 1:
			case 4:
				this.answer2Button.setVisibility(View.INVISIBLE);
				this.answer3Button.setVisibility(View.INVISIBLE);
				return;
			case 2:
			case 3:
				this.answer1Button.setVisibility(View.INVISIBLE);
				this.answer4Button.setVisibility(View.INVISIBLE);
		}
	}
	@SuppressWarnings("all") // I ain't fixin your shit bruv
	@RequiresApi(api = Build.VERSION_CODES.N)
	public void fireAskAudienceButton(View view) {
		this.askAudienceButton.setVisibility(View.INVISIBLE);
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
				this.answer1Button.setText(this.answer1Button.getText() + " " + results.get(0));
				this.answer2Button.setText(this.answer2Button.getText() + " " + results.get(0));
				this.answer3Button.setText(this.answer3Button.getText() + " " + results.get(0));
				this.answer4Button.setText(this.answer4Button.getText() + " " + results.get(0));
				return;
			case 2:
				this.answer2Button.setText(this.answer1Button.getText() + " " + results.get(0));
				this.answer1Button.setText(this.answer2Button.getText() + " " + results.get(0));
				this.answer3Button.setText(this.answer3Button.getText() + " " + results.get(0));
				this.answer4Button.setText(this.answer4Button.getText() + " " + results.get(0));
				return;
			case 3:
				this.answer3Button.setText(this.answer1Button.getText() + " " + results.get(0));
				this.answer2Button.setText(this.answer2Button.getText() + " " + results.get(0));
				this.answer1Button.setText(this.answer3Button.getText() + " " + results.get(2));
				this.answer4Button.setText(this.answer4Button.getText() + " " + results.get(3));
				return;
			case 4:
				this.answer4Button.setText(this.answer1Button.getText() + " " + results.get(0));
				this.answer2Button.setText(this.answer2Button.getText() + " " + results.get(1));
				this.answer1Button.setText(this.answer3Button.getText() + " " + results.get(2));
				this.answer3Button.setText(this.answer4Button.getText() + " " + results.get(3));
		}
	}
	@SuppressWarnings("all")
	public void fireCallFriendButton(View view) {
		this.callFriendButton.setVisibility(View.INVISIBLE);
		Random rand = new Random();
		// Siema tu jak masz numer pytania mniejszy niż 5 to zawsze dobra odpowiedź jest
		// zwracana
		if (currentQuestion.getPrize() < 20000) {
			this.helpText
					.setText(currentQuestion.getAnswersArray()[currentQuestion.getCorrect() - 1]);
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				this.helpText.setText(
						currentQuestion.getAnswersArray()[rand.ints(0, 3).findFirst().getAsInt()]);
			}
		}
	}
	
}