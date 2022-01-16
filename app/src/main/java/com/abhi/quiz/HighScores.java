package com.abhi.quiz;

/*
Created by Abhishek on 7/11/2019
*/

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.TextView;

import com.abhi.quiz.db.DbOperations;

public class HighScores extends Activity {

	private SQLiteDatabase db;
	private Cursor cursor;
	TextView textViewName,textViewScore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		textViewName = findViewById(R.id.textView1);
		textViewScore = findViewById(R.id.textView2);
		DbOperations dbOperations = new DbOperations(this);

		cursor = dbOperations.selectScoresQuery();
		if(!cursor.moveToFirst())
			textViewName.setText("No high scores yet!");
		else
		{
			textViewName.setText(cursor.getString(0));
			textViewScore.setText(cursor.getString(1));
		}
	}

}
