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

public class KJSCE extends Activity {
	
	final String myTag = "DocsUpload";
	String Name,Rollno,Phone,Email,Branch,Year,Division,Event;
	EditText name,rollno,phone,email;
	Spinner branch,year,division,event;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);

				//Remove notification bar
				this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.regform);
		name=(EditText)findViewById(R.id.name);
		rollno=(EditText)findViewById(R.id.rollno);
		phone=(EditText)findViewById(R.id.phone);
		email=(EditText)findViewById(R.id.email);
		
		tv=(TextView)findViewById(R.id.tv);
		
		branch=(Spinner)findViewById(R.id.branch);
		year=(Spinner)findViewById(R.id.year);
		division=(Spinner)findViewById(R.id.divison);
		event=(Spinner)findViewById(R.id.event);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Segoe_UI.ttf");
		
		name.setTypeface(type);
		rollno.setTypeface(type);
		phone.setTypeface(type);
		email.setTypeface(type);
		tv.setTypeface(type);
		
		Log.i(myTag, "OnCreate()");
		
	}

	public void register(View v)
	{
		Name=name.getText().toString();
		Rollno=rollno.getText().toString();
		Phone=phone.getText().toString();
		Email=email.getText().toString();
		
		Branch=branch.getSelectedItem().toString();
		Year=year.getSelectedItem().toString();
		Division=division.getSelectedItem().toString();
		Event=event.getSelectedItem().toString();
		
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				postData(Name,Rollno, Phone,Email,Branch,Year,Division,Event);	
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

	public void postData(String Name,String Rollno,String Phone,String Email,String Branch,String Year,String Division,String Event) {

		String fullUrl = "https://docs.google.com/forms/d/1b9IgrWhT-6rlkPvhatF3TAymJzQLRnbNBf0zLXVVYX0/formResponse";
		HttpRequest mReq = new HttpRequest();
		
		String data =   "entry.1344341921=" + URLEncoder.encode(Name) + "&" + 
					    "entry.1225752206=" + URLEncoder.encode(Rollno) + "&" +
						"entry.282902304=" + URLEncoder.encode(Branch) + "&" +
						"entry.438644966=" + URLEncoder.encode(Year) + "&" +
						"entry.1475516363=" + URLEncoder.encode(Division) + "&" +
						"entry.1617444505=" + URLEncoder.encode(Event) + "&" +
						"entry.942733818=" + URLEncoder.encode(Phone) + "&" +
					    "entry.1131673849=" + URLEncoder.encode(Email);
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

