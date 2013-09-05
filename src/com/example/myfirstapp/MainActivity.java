package com.example.myfirstapp;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
// import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v4.app.DialogFragment;

@SuppressLint({ "ValidFragment", "NewApi" })
public class MainActivity extends FragmentActivity {

//	public final static String FIO = "com.example.myfirstapp.MESSAGE";
//	public final static String BORN_DATE = "com.example.myfirstapp.MESSAGE";
	static final private int GET_ANSWER = 0;
	public static String hhAnswer = "Принято";
	
	EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex_array, android.R.layout.simple_spinner_item);

//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//    	Spinner spinner = (Spinner) findViewById(R.id.sex_spinner);
//    	spinner.setAdapter(adapter);
//    	spinner.setPrompt("Пол");
//   	spinner.setSelection(0);

    }
    
    
	public void selectDate(View view) {
		DialogFragment newFragment = new SelectDateFragment();
		newFragment.show(getSupportFragmentManager(), "DatePicker");
	}
	
	public void populateSetDate(int year, int month, int day) {
		mEdit = (EditText)findViewById(R.id.bornDate);
		mEdit.setText(day+"."+month+"."+year);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user clicks the Send button */
    @SuppressLint("NewApi")
	public void sendMessage(View view) {
       
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	
    	
    	
    	EditText editFio = (EditText) findViewById(R.id.fio);
    	intent.putExtra("fio", editFio.getText().toString());
 
    	EditText editBornDate = (EditText) findViewById(R.id.bornDate);
    	intent.putExtra("bornDate", editBornDate.getText().toString());

    	Spinner spinner = (Spinner) findViewById(R.id.sex_spinner);
    	intent.putExtra("sex", spinner.getSelectedItem().toString());
    	
    	EditText editDolg = (EditText) findViewById(R.id.dolg);
    	intent.putExtra("dolg", editDolg.getText().toString());

    	EditText editSalary = (EditText) findViewById(R.id.salary);
    	intent.putExtra("salary", editSalary.getText().toString());
 
    	EditText editPhone = (EditText) findViewById(R.id.phone);
    	intent.putExtra("phone", editPhone.getText().toString());
    	
    	EditText editEmail = (EditText) findViewById(R.id.email);
    	intent.putExtra("email", editEmail.getText().toString());
    	
    	startActivityForResult(intent, GET_ANSWER);
	
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);

    	if (requestCode == GET_ANSWER) {
    		if (resultCode == RESULT_OK) {
    			hhAnswer = data.getStringExtra(DisplayMessageActivity.HH_ANSWER);
    			//hhAnswer = 
    			DialogFragment newFragment = new ShowAnswerFragment();
    			newFragment.show(getSupportFragmentManager(), "DatePicker");
    		}else {
    		//	DialogFragment newFragment = new SelectDateFragment();
    		//	newFragment.show(getSupportFragmentManager(), "DatePicker");
    		}
    	}
    }
    
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    	@SuppressLint("NewApi")
		@Override
    	public Dialog onCreateDialog(Bundle savedInstanceState) {
    	final Calendar calendar = Calendar.getInstance();
    	int yy = calendar.get(Calendar.YEAR);
    	int mm = calendar.get(Calendar.MONTH);
    	int dd = calendar.get(Calendar.DAY_OF_MONTH);
    	return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    	}
    	 
    	public void onDateSet(DatePicker view, int yy, int mm, int dd) {
    	populateSetDate(yy, mm+1, dd);
    	}
    }
    
    public class ShowAnswerFragment extends DialogFragment implements OnClickListener {

    	  final String LOG_TAG = "myLogs";

    	  public Dialog onCreateDialog(Bundle savedInstanceState) {
    		
    	//	Intent intent = getIntent();
    	//	String answer = intent.getStringExtra(DisplayMessageActivity.HH_ANSWER);
    		
    	    AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
    	        .setTitle(R.string.answerDialogTitle).setPositiveButton(R.string.yes, this)
    	        .setMessage(hhAnswer);
    	    return adb.create();
    	  }

    	  public void onClick(DialogInterface dialog, int which) {
    	    int i = 0;
    	    switch (which) {
    	    case Dialog.BUTTON_POSITIVE:
    	      i = R.string.yes;
    	      break;

    	    }
    	    if (i > 0)
    	      Log.d(LOG_TAG, "Dialog 2: " + getResources().getString(i));
    	  }

    	  public void onDismiss(DialogInterface dialog) {
    	    super.onDismiss(dialog);
    	    Log.d(LOG_TAG, "Dialog 2: onDismiss");
    	  }

    	  public void onCancel(DialogInterface dialog) {
    	    super.onCancel(dialog);
    	    Log.d(LOG_TAG, "Dialog 2: onCancel");
    	  }
    	}
    
}
