package com.example.myfirstapp;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import android.widget.Toast;
import android.support.v4.app.DialogFragment;

@SuppressLint({ "ValidFragment", "NewApi" })
public class MainActivity extends FragmentActivity {

	static final private int GET_ANSWER = 0;
	public static String hhAnswer = "Принято";
	
	EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex_array, android.R.layout.simple_spinner_item);

//      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//    	Spinner spinner = (Spinner) findViewById(R.id.sex_spinner);
//    	spinner.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user clicks the Send button */
    @SuppressLint("NewApi")
	public void sendMessage(View view) {
       
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	// проверка ввода и перадача ФИО
    	EditText editFio = (EditText) findViewById(R.id.fio);
    	if (editFio.getText().toString().equals(""))
    	{
    		Toast.makeText(this, "Вы не ввели Фамилию Имя Отчество", Toast.LENGTH_SHORT).show();
    		editFio.requestFocus();
    	    return;
    	}
    	else
    	{
    		intent.putExtra("fio", editFio.getText().toString());
    	}
    	// проверка ввода и перадача Даты рождения
    	EditText editBornDate = (EditText) findViewById(R.id.bornDate);
    	if (editBornDate.getText().toString().equals(""))
    	{
    		Toast.makeText(this, "Вы не ввели Дату рождения", Toast.LENGTH_SHORT).show();
    		selectDate((View) findViewById(R.id.bornDate));
    	    return;
    	}
    	else
    	{
    		intent.putExtra("bornDate", editBornDate.getText().toString());
    	}
    	

    	Spinner spinner = (Spinner) findViewById(R.id.sex_spinner);
    	intent.putExtra("sex", spinner.getSelectedItem().toString());
    	
    	// проверка ввода и перадача Должности
    	EditText editDolg = (EditText) findViewById(R.id.dolg);
    	if (editDolg.getText().toString().equals(""))
    	{
    		Toast.makeText(this, "Вы не ввели Должность", Toast.LENGTH_SHORT).show();
    		editDolg.requestFocus();
    	    return;
    	}
    	else
    	{
    		intent.putExtra("dolg", editDolg.getText().toString());
    	}
    	
    	
    	// проверка ввода и перадача Должности
    	EditText editSalary = (EditText) findViewById(R.id.salary);
    	if (editSalary.getText().toString().equals(""))
    	{
    		Toast.makeText(this, "Вы не ввели Зарплату", Toast.LENGTH_SHORT).show();
    		editSalary.requestFocus();
    	    return;
    	}
    	else
    	{
    		intent.putExtra("salary", editSalary.getText().toString());
    	}
    	
    	EditText editPhone = (EditText) findViewById(R.id.phone);
    	if (editPhone.getText().toString().equals(""))
    	{
    		Toast.makeText(this, "Вы не ввели Номер телефона", Toast.LENGTH_SHORT).show();
    		editPhone.requestFocus();
    	    return;
    	}
    	else
    	{	
    		if (isPhoneValid(editPhone.getText().toString())) {
    			intent.putExtra("phone", editPhone.getText().toString());
    		} else {
    			Toast.makeText(this, "Вы ввели не корректный номер телефона "+"\n"+"введите номер вида +ХХХХХХХХХХХ ", Toast.LENGTH_SHORT).show();
        		editPhone.requestFocus();
        	    return;
    		}
        	
    	}

    	
    	EditText editEmail = (EditText) findViewById(R.id.email);
    	if (editEmail.getText().toString().equals(""))
    	{
    		Toast.makeText(this, "Вы не ввели E-Mail", Toast.LENGTH_SHORT).show();
    		editEmail.requestFocus();
    	    return;
    	}
    	else
    	{
    		if (isEmailValid(editEmail.getText().toString())) { 
    			intent.putExtra("email", editEmail.getText().toString());
    		} else {
    			Toast.makeText(this, "Вы ввели не корректный E-Mail", Toast.LENGTH_SHORT).show();
        		editEmail.requestFocus();
        	    return;
    		}
        	
    	}

    	
    	startActivityForResult(intent, GET_ANSWER);
	
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);

    	if (requestCode == GET_ANSWER) {
    		if (resultCode == RESULT_OK) {
    			hhAnswer = data.getStringExtra(DisplayMessageActivity.HH_ANSWER);

    			DialogFragment newFragment = new ShowAnswerFragment();
    			newFragment.show(getSupportFragmentManager(), "Answer");
    		} else {
    			//DialogFragment newFragment = new ShowAnswerFragment();
    			//newFragment.show(getSupportFragmentManager(), "Answer");
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
    	  
    }
    
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    
    public static boolean isPhoneValid(String phone) {
        boolean isValid = false;

        String expression = "^[+]?[0-9]{10,13}$";
        CharSequence inputStr = phone;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    
    
        
}
