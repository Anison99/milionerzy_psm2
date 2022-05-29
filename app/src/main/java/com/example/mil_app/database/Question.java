package com.example.mil_app.database;

import android.content.res.Resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question {
	
	private String[] answersArray = new String[4];
	private byte correct;
	private int prize;
	private String questionString;

	public Question(int questionNumber, String question, String[] answersArray, byte correct, int prize) {
	}

	public String getQuestionString() {
		return questionString;
	}
	
	private enum Answer {
		FIRST, SECOND, THIRD, FOURTH
	}
	
	/**
	 *
	 * @param which {@link Answer} enum poining to which question from array do we
	 *              want to get (FIRST,SECOND,THIRD,FOURTH)
	 * @return {@link String} literal of answer. Ready to be inserted into button
	 */
	public String getAnswer(Answer which) {
		switch (which) {
			case FIRST:
				return answersArray[0];
			case SECOND:
				return answersArray[1];
			case THIRD:
				return answersArray[2];
			case FOURTH:
				return answersArray[3];
			default:
				return null;
		}
	}
	
	public String[] getAnswersArray() {
		return answersArray;
	}
	
	/**
	 *
	 * @return Correct answer number from {@code 1} to {@code 4} of this
	 *         {@link Question} object
	 */
	public byte getCorrect() {
		return correct;
	}
	
	public int getPrize() {
		return prize;
	}
	
	protected Question(ResultSet resultSet, int prize) {
		try {
			resultSet.first();
			for (int i = 0; i < 3; i++) {
				this.answersArray[i] = resultSet.getString(i + 1);
			}
			this.correct = resultSet.getByte("correct");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *
	 * @param prize Prize money expected for this question as in database
	 * @return {@link Question} object
	 */
	public static Question getRandomQuestionfromDatabase(int prize) {
		Connection connection = null;
		try {
			StringBuilder sB = new StringBuilder("jdbc:sqlite:");
			sB.append(System.getProperty("user.dir")); // project location
			sB.append("app/src/main/res/raw/milionerzy.db");
			connection = DriverManager.getConnection(sB.toString());
			PreparedStatement statement = connection
					.prepareStatement("SELECT question,answer1,answer2,answer3,answer4,correct"
							+ " FROM Questions WHERE prize=" + prize + " ORDER BY random() LIMIT 1;");
			ResultSet results = statement.executeQuery();
			return new Question(results, prize);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

//	public static Question getRandomFromCSV
	
}
