package com.example.mil_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper{

        private static final String QUESTIONS= "questionsdb"; // database name
        private static final String ID_COL = "id"; // id column
        private static final String QUESTION = "question"; // show question
        private static final String ANSWER_1 = "answer1"; // answers
        private static final String ANSWER_2 = "answer2";
        private static final String ANSWER_3 = "answer3";
        private static final String ANSWER_4 = "answer4";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * making rows and columns in database
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + QUESTIONS + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QUESTION + " TEXT,"
                + ANSWER_1 + " TEXT,"
                + ANSWER_2 + " TEXT,"
                + ANSWER_3 + " TEXT,"
                + ANSWER_4 + " TEXT)";
        db.execSQL(query);

    }
    public void addNewQuestion(String questionName, String ans1, String ans2, String ans3, String ans4) {
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS);
        onCreate(db);

    }
}
