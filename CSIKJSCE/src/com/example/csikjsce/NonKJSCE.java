package com.example.csikjsce;


import java.net.URLEncoder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NonKJSCE extends Activity {
	
	final String myTag = "DocsUpload";
	String Name,College,Phone,Email,Event;
	EditText name,college,phone,email;
	Spinner event;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);

				//Remove notification bar
				this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.nonkjsce);
		name=(EditText)findViewById(R.id.name);
		college=(EditText)findViewById(R.id.college);
		phone=(EditText)findViewById(R.id.phone);
		email=(EditText)findViewById(R.id.email);
		
		tv=(TextView)findViewById(R.id.tv);
		
		event=(Spinner)findViewById(R.id.event);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Segoe_UI.ttf"); 
		
		name.setTypeface(type);
		college.setTypeface(type);
		phone.setTypeface(type);
		email.setTypeface(type);
		tv.setTypeface(type);

		Log.i(myTag, "OnCreate()");
		
	}

	public void register(View v)
	{
		Name=name.getText().toString();
		Phone=phone.getText().toString();
		Email=email.getText().toString();
		College=college.getText().toString();
		Event=event.getSelectedItem().toString();
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				postData(Name,College, Phone,Email,Event);
				
			}
		});
		t.start();

		if(checkInternetConnection())
	      {
	    	 Toast.makeText(getApplicationContext(), "Thank You For Registration!",Toast.LENGTH_LONG ).show();
	      }
	       else
	    	  {
	    	  Toast.makeText(getApplicationContext(), "Registration Failed! Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
	    	  }
		Intent intent = new Intent(getApplicationContext(), SelectType.class);
	    startActivity(intent);
	}

	public void postData(String Name,String College,String Phone,String Email,String Event) {

		String fullUrl = "https://docs.google.com/forms/d/1FyLEUq5HgEoYW_x8ZnjYg_ezqASXpFO0eV8VvWRQE2g/formResponse";
		HttpRequest mReq = new HttpRequest();
		
		String data =   "entry.1221916451=" + URLEncoder.encode(Name) + "&" + 
						"entry.1767266814=" + URLEncoder.encode(Event) + "&" +
						"entry.1265819096=" + URLEncoder.encode(College) + "&" +
						"entry.1429905988=" + URLEncoder.encode(Phone) + "&" +
					    "entry.1739632142=" + URLEncoder.encode(Email);
		String response = mReq.sendPost(fullUrl, data);
		Log.i(myTag, response);
	} 
	
	public boolean checkInternetConnection() {

    	ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

    	// ARE WE CONNECTED TO THE NET

    	if (conMgr.getActiveNetworkInfo() != null

    	&& conMgr.getActiveNetworkInfo().isAvailable()

    	&& conMgr.getActiveNetworkInfo().isConnected()) {

    	return true;

    	} else {


    	return false;

    	}

    	}

}

