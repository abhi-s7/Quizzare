package com.abhi.quiz;

/*
Created by Abhishek on 7/11/2019
*/

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.abhi.quiz.db.DbOperations;

public class FinalActivity extends Activity {

	TextView tv,tv2;
	private SQLiteDatabase db;
	private Cursor cursor;
	Button btn;
	int score;
	String userName,query;
	private static final String x="SELECT * FROM scores";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);
		tv=(TextView)findViewById(R.id.textView3);
		tv2=(TextView)findViewById(R.id.textView2);
		btn=(Button)findViewById(R.id.home_btn);

		Bundle data=getIntent().getExtras();
		score=data.getInt("score");
		userName=data.getString("userName");
		tv.setText("Score: " + score);
		DbOperations dbOperations = new DbOperations(this);
		cursor = dbOperations.selectScoresQuery();
		if(!cursor.moveToFirst())
		{
			dbOperations.insertNewHighScore(userName, score);
			tv2.setText("You got a high score!");
		}
		else
		{
			cursor.moveToLast();
			if(score > Integer.parseInt(cursor.getString(1)))
			{
				dbOperations.updateNewHighScore(userName, score, cursor.getString(1));
				db.execSQL(query);
				tv2.setText("You got a high score!");
			}
		}
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), HomeScreen.class);
				i.putExtra("userName", userName);
				startActivity(i);
			}
		});
	}


}
