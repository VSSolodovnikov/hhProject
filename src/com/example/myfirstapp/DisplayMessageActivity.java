package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class DisplayMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_display_message);

	    // Get the message from the intent
	    Intent intent = getIntent();
	    
	    String fio = intent.getStringExtra("fio");
	    TextView gettedFIO = (TextView)findViewById(R.id.textViewFio);
	    gettedFIO.setTextSize(30);
	    gettedFIO.setText(fio);
	    
	    String bornDate = intent.getStringExtra("bornDate");
	    TextView gettedBornDate = (TextView)findViewById(R.id.dateOfBorn);
	    gettedBornDate.setTextSize(30);
	    gettedBornDate.setText(bornDate);
	    
	    String sex = intent.getStringExtra("sex");
	    TextView gettedSex = (TextView)findViewById(R.id.sex);
	    gettedSex.setTextSize(30);
	    gettedSex.setText(sex);
	    
	    String dolg = intent.getStringExtra("dolg");
	    TextView gettedDolg = (TextView)findViewById(R.id.dolg);
	    gettedDolg.setTextSize(30);
	    gettedDolg.setText(dolg);

	    String salary = intent.getStringExtra("salary");
	    TextView gettedSalary = (TextView)findViewById(R.id.salary);
	    gettedSalary.setTextSize(30);
	    gettedSalary.setText(salary);
	    
	    String phone = intent.getStringExtra("phone");
	    TextView gettedPhone = (TextView)findViewById(R.id.phone);
	    gettedPhone.setTextSize(30);
	    gettedPhone.setText(phone);
	    
	    String email = intent.getStringExtra("email");
	    TextView gettedEmail = (TextView)findViewById(R.id.email);
	    gettedEmail.setTextSize(30);
	    gettedEmail.setText(email);
	    
	    
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
