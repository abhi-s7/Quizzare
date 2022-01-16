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
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends Activity {

	public static final String MY_PREFERENCES="myPreferences";
	public static final String NAME_KEY = "nameKey";
	private SharedPreferences sharedPreferences;
	EditText et1,et2;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		et1= findViewById(R.id.editText1);
		btn=  findViewById(R.id.login_btn);
		sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
		String val = sharedPreferences.getString(NAME_KEY, null);
		if(val!=null)
		{
			Intent i=new Intent(getApplicationContext(), HomeScreen.class);
			i.putExtra("userName", val);
			startActivity(i);
		}
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name=et1.getText().toString();
				if(name.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Name required...", Toast.LENGTH_LONG).show();
					return;
				}
				SharedPreferences.Editor editor=sharedPreferences.edit();
				editor.putString(NAME_KEY, name);
				editor.commit();
				Intent i=new Intent(getApplicationContext(), HomeScreen.class);
				i.putExtra("userName", name);
				startActivity(i);
			}
		});
	}

}
