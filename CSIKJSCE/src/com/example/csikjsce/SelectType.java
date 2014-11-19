package com.example.csikjsce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
public class SelectType extends Activity {

	
	Button kjsce,nonkjsce;
	TextView tv1,tv2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Remove title bar
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);

				//Remove notification bar
				this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.select_type);
		kjsce=(Button)findViewById(R.id.kjsce);
		nonkjsce=(Button)findViewById(R.id.nonkjsce);
		tv1=(TextView)findViewById(R.id.tv1);
		tv2=(TextView)findViewById(R.id.tv2);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Segoe_UI.ttf");
		tv1.setTypeface(type);
		tv2.setTypeface(type);
	}
	public void KJSCE(View v)
	{
		startActivity(new Intent(getApplicationContext(), KJSCE.class));
	}
	
	public void nonKJSCE(View v)
	{
		startActivity(new Intent(getApplicationContext(), NonKJSCE.class));
	}

}
