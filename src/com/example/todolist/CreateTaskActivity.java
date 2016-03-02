//Group9A_HW02
//Ram Prasad Narayanaswamy
//Aaron Maisto

package com.example.todolist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

public class CreateTaskActivity extends Activity{

	EditText editTextTitle;
	EditText editTextDate;
	EditText editTextTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_task);
		
		editTextTitle = (EditText) findViewById(R.id.editTextTitle);
		editTextDate = (EditText) findViewById(R.id.editTextDate);
		editTextDate.setKeyListener(null);
		editTextTime = (EditText) findViewById(R.id.editTextTime);
		editTextTime.setKeyListener(null);
		
		TimePickerFragment tpf = new TimePickerFragment();
		tpf.dateTimePickerDialogDisplay(CreateTaskActivity.this,editTextDate, editTextTime);
		
		
		Button btn = (Button) findViewById(R.id.buttonSave);
		btn.setOnClickListener(new View.OnClickListener() {
		
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 
				 String taskTitle;
				 String taskDate;
				 String taskTime;
				 int check =0;
				 
				 if (editTextTitle.getText() == null || editTextTitle.getText().toString().length() == 0){
					 editTextTitle.setError("Please enter a title."); 
					 check = 1;
				 }
				 else{
					 editTextTitle.setError(null);
				 }
				 if (editTextDate.getText() == null || editTextDate.getText().toString().length() == 0){ 
					 editTextDate.setError("Please select a date.");
					 check = 1;
				 }
				 else{
					 editTextDate.setError(null);
				 }
				 if (editTextTime.getText() == null || editTextTime.getText().toString().length() == 0) {
					 editTextTime.setError("Please select a time."); 
					 check = 1;
				 }
				 else{
					 editTextTime.setError(null);
				 }
				 if(check == 0){
					 taskTitle = editTextTitle.getText().toString();
					 taskDate = editTextDate.getText().toString();
					 taskTime = editTextTime.getText().toString();
					 
					 
					 RadioGroup priorityGroup = (RadioGroup) findViewById(R.id.radioGroupPriority);
					 int priority;
					 if(priorityGroup.getCheckedRadioButtonId() == R.id.radioHigh){
						 priority = 3;
					 }
					 else if(priorityGroup.getCheckedRadioButtonId() == R.id.radioMedium){
						 priority = 2;
					 }
					 else
					 {
						 priority = 1;
					 }
					 
					Task task = new Task(taskTitle, taskDate,taskTime,priority);				
					Intent intent = new Intent();
					intent.putExtra("TaskObject", task);
					setResult(RESULT_OK, intent);
					finish();
				 }
				 
				
				}
		});
		
		
	}
	
}
