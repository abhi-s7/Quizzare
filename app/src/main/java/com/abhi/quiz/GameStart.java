package com.abhi.quiz;

/*
Created by Abhishek on 7/11/2019
*/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.quiz.db.DbOperations;

public class GameStart extends Activity {

	RadioGroup radioGroup;
	TextView tv;
	Button btn;
	private Cursor cursor;
	String userName;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_start);

		DbOperations dbOperations = new DbOperations(this);
		Bundle data=getIntent().getExtras();
		userName = data.getString("userName");
		btn= findViewById(R.id.proceed_btn);

		cursor = dbOperations.selectQuestionsQuery();
		if(!cursor.moveToFirst()){
			dbOperations.insertDB();
		}
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), QuizActivity.class);
				i.putExtra("userName", userName);
				startActivity(i);
			}
		});
		
	}
}
