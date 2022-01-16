package com.abhi.quiz;

/*
Created by Abhishek on 7/11/2019
*/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends Activity {

	TextView tv;
	String userName;
	Button logoutBtn, startGameBtn, viewHighScoreBtn;
	SharedPreferences sharedpreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		tv = findViewById(R.id.textView1);
		logoutBtn = findViewById(R.id.logout_btn);
		startGameBtn = findViewById(R.id.start_game);
		viewHighScoreBtn = findViewById(R.id.view_highscore);

		Bundle data = getIntent().getExtras();
		userName = data.getString("userName");
		tv.setText("Welcome : " + userName);

		logoutBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sharedpreferences=getSharedPreferences(LoginScreen.MY_PREFERENCES, Context.MODE_PRIVATE);
			    SharedPreferences.Editor editor=sharedpreferences.edit();
			    editor.clear();
			    editor.commit();
			    Intent i=new Intent(getApplicationContext(),LoginScreen.class);
			    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    finish();
			    startActivity(i);
			}
		});

		startGameBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(getApplicationContext(), GameStart.class);
				i.putExtra("userName", userName);
				startActivity(i);
			}
		});

		viewHighScoreBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(getApplicationContext(),HighScores.class);
				i.putExtra("userName", userName);
				startActivity(i);
			}
		});
	}
}
