//Group9A_HW02
//Ram Prasad Narayanaswamy
//Aaron Maisto

package com.example.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditTaskActivity extends Activity {

	EditText editTextTitle;
	EditText editTextDate;
	EditText editTextTime;
	Task task;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_task);
		
		task = (Task) getIntent().getExtras().getSerializable("TaskObject");
		
		String taskName = task.getName();
		String taskDate = task.getDate();
		String taskTime = task.getTime();
		int priority = task.getPriority();
		
		editTextTitle = (EditText) findViewById(R.id.editTextTitle);
		editTextTitle.setText(taskName);
		editTextDate = (EditText) findViewById(R.id.editTextDate);
		editTextDate.setKeyListener(null);
		editTextDate.setText(taskDate);
		editTextTime = (EditText) findViewById(R.id.editTextTime);
		editTextTime.setKeyListener(null);
		editTextTime.setText(taskTime);
		
		TimePickerFragment tpf = new TimePickerFragment();
		tpf.dateTimePickerDialogDisplay(EditTaskActivity.this,editTextDate, editTextTime);

		
		RadioGroup priorityGroup = (RadioGroup) findViewById(R.id.radioGroupPriority);
		if(priority == 3){
			priorityGroup.check(R.id.radioHigh);
		}
		else if(priority == 2){
			priorityGroup.check(R.id.radioMedium);
		}
		else
		{
			priorityGroup.check(R.id.radioLow);
		}
		
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
				
				Task task = new Task(taskTitle,taskDate,taskTime,priority);				
				Intent intent = new Intent();
				intent.putExtra("TaskObject", task);
				setResult(RESULT_OK, intent);
				finish();
				}
				}
		});
		
		
		
	}
}
