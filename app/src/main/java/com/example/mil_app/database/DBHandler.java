package com.example.mil_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
	
	public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "milionerzy.db"; // database name
	
	/**
	 * inner class that defines the table contents
	 */
	public static class FeedEntry implements BaseColumns {
		private static final String ID_COL = "id"; // id column
		private static final String QUESTION = "question"; // show question
		private static final String ANSWER_1 = "answer1"; // answers
		private static final String ANSWER_2 = "answer2";
		private static final String ANSWER_3 = "answer3";
		private static final String ANSWER_4 = "answer4";
		
	}
	
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + DATABASE_NAME + " ("
			+ FeedEntry.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ FeedEntry.QUESTION + " TEXT,"
			+ FeedEntry.ANSWER_1 + " TEXT,"
			+ FeedEntry.ANSWER_2 + " TEXT,"
			+ FeedEntry.ANSWER_3 + " TEXT,"
			+ FeedEntry.ANSWER_4 + " TEXT)";
	private static final String SQL_DELETE_ENTRIES = null;
	
	/**
	 * making rows and columns in database
	 *
	 * @param db
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}
}


// ---------------- FOR TESTS -------------------
 /*  public void addNewQuestion(String questionName, String ans1, String ans2, String ans3, String ans4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(QUESTION, questionName);
        values.put(ANSWER_1, ans1);
        values.put(ANSWER_2, ans2);
        values.put(ANSWER_3, ans3);
        values.put(ANSWER_4, ans4);

        db.insert(QUESTIONS, null, values);
        db.close();

    }
*/