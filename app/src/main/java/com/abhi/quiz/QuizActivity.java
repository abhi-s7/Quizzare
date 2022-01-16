package com.abhi.quiz;
/*
Created by Abhishek on 7/11/2019
*/

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.quiz.db.DbOperations;

public class QuizActivity extends Activity {

	TextView tv,tv2,tv3,tv4;
	RadioGroup rg;
	Button btn;
	ProgressBar pb;
	int score, netScore,questions=0;
	private Cursor cursor;
	CountDownTimer timer;
	String userName;
	int answeredQuestion[] = new int[5];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		tv=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		tv3=(TextView)findViewById(R.id.textView3);
		tv4=(TextView)findViewById(R.id.textView4);
		rg=(RadioGroup)findViewById(R.id.radioGroup1);
		btn=(Button)findViewById(R.id.submit_btn);
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		pb.setProgress(100);
		Bundle data=getIntent().getExtras();
		userName=data.getString("userName");
		//tv.setText("");
		DbOperations dbOperations = new DbOperations(this);

		//dbOperations.openDatabase();
		cursor =dbOperations.selectQuestionsQuery();
		cursor.moveToFirst();
		getQuestion();
		tv2.setText("Score: 0");
		startCount();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int res=rg.getCheckedRadioButtonId();
				switch(res)
				{
				case 0:
				case 1:
				case 2:
				case 3:
					break;
				default:
						Toast.makeText(getApplicationContext(), "Select at least one option", Toast.LENGTH_SHORT).show();
						return;
				}
				if(res==Integer.parseInt(cursor.getString(6)) - 1)
					{
						Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
						score += netScore;
						tv2.setText("Score: "+ score);
					}
				else
				{
//					mp=MediaPlayer.create(getApplicationContext(), R.raw.alert2);
//					mp.start();
					Toast.makeText(getApplicationContext(), "Incorrect Answer!", Toast.LENGTH_SHORT).show();
				}
				if(questions<5)
				{
					//cursor.moveToNext();
					rg.removeAllViews();
					getQuestion();
					timer.cancel();
					pb.setProgress(100);
					startCount();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "You have completed the quiz", Toast.LENGTH_SHORT).show();
					timer.cancel();
					Intent i=new Intent(getApplicationContext(),FinalActivity.class);
					i.putExtra("score", score);
					i.putExtra("userName", userName);
					startActivity(i);
				}
			}
		});
	}

	protected void getQuestion()
	{
		boolean f=false;int qid;
		do {
			qid=(int) Math.floor(Math.random()*20);
			for(int i=0;i<questions;i++)
			{
				if(qid == answeredQuestion[i])
				{
					f=true;
					break;
				}
			}
		} while (f); 
		
		cursor.moveToFirst();
		boolean flag=true;
		while (flag) {
			if(qid!=Integer.parseInt(cursor.getString(0)))
			{
				if(!cursor.isLast())
					cursor.moveToNext();
				else
					cursor.moveToFirst();
			}
			else
				flag=false;
		}
		tv.setText(cursor.getString(1));
		for(int i=0;i<=3;i++)
		{
			RadioButton rb=new RadioButton(this);
			rb.setId(i);
			rb.setText(cursor.getString(i+2));
			rb.setTextColor(getResources().getColor(R.color.lightGrey));
			rg.addView(rb);			
		}
		rg.clearCheck();
		answeredQuestion[questions] = qid;
		questions++;
	}
	protected void startCount() {
		tv4.setText("Seconds Remaining");
		btn.setText("SUBMIT");
		timer=new CountDownTimer(30000, 1000) {

            
			public void onTick(long millisUntilFinished) {
                tv3.setText("" + millisUntilFinished / 1000);
                netScore =(int)millisUntilFinished/2000;
                pb.setProgress(pb.getProgress()-3);
            }

            public void onFinish() {
            	pb.setProgress(0);
            	tv3.setText("");
                tv4.setText("Sorry...Time Up!");
                //btn.setClickable(false);
//                btn.setText("Move to next");
//                getQuestion();
            }
         }.start();
	}

}
