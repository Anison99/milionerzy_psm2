package com.example.mil_app.database;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mil_app.App;
import com.example.mil_app.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class CSVController extends AppCompatActivity {
	/**
	 * Amount of rows associated with question in each individual .csv file.
	 */
	private static final int LINES_IN_CSV = 10;
	private static final String SPLIT_DELIM = ";";
	
	/**
	 * @param csvNumber Which csv file.
	 * @return {@link Question} object
	 */
	public static Question getRandomQuestion(int csvNumber) {
		int resolvedResource = switch (csvNumber) {
			case 1 -> R.raw.q1;
			case 2 -> R.raw.q2;
			case 3 -> R.raw.q3;
			case 4 -> R.raw.q4;
			case 5 -> R.raw.q5;
			case 6 -> R.raw.q6;
			case 7 -> R.raw.q7;
			case 8 -> R.raw.q8;
			case 9 -> R.raw.q9;
			case 10 ->R.raw.q10;
			case 11 -> R.raw.q11;
			case 12 -> R.raw.q12;
			default -> throw new IllegalStateException("Unexpected value: " + csvNumber);
		};
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(App.getInstance().getResources().openRawResource(resolvedResource)));
			int rand = new Random().nextInt(LINES_IN_CSV); // random from 0 to lines-1
			System.out.println("Random:"+rand);
			for (int i = 0; i <= rand - 1; i++) {
				br.readLine();
			}
			String line = br.readLine();
			br.close();
			String[] record = line.split(SPLIT_DELIM);
			// question;answer1;answer2;answer3;answer4;correct
			String question = record[0];
			byte correct = Byte.parseByte(record[5]);
			String[] answersArray = new String[4];
			System.arraycopy(record, 1, answersArray, 0, 4);
			return new Question(csvNumber, question, answersArray, correct,
					getPrize(csvNumber));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private static int getPrize(int stageNumber) {
		return switch (stageNumber) {
			case 1 -> 500;
			case 2 -> 1000;
			case 3 -> 2000;
			case 4 -> 5000;
			case 5 -> 10000;
			case 6 -> 20000;
			case 7 -> 40000;
			case 8 -> 75000;
			case 9 -> 125000;
			case 10 -> 250000;
			case 11 -> 500000;
			case 12 -> 1000000;
			default -> stageNumber;
		};
	}
}
