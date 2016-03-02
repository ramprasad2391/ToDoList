package com.example.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayActivity extends Activity {

	Task task;
	int taskPosition;
	
	public void displayTask(Task task){
		
		this.task = task;
		String taskName = task.getName();
		String taskDate = task.getDate();
		String taskTime = task.getTime();
		String priority;
		int priorityValue = task.getPriority();
		if(priorityValue == 3){
			 priority = "High";
		 }
		 else if(priorityValue == 2){
			 priority = "Medium";
		 }
		 else
		 {
			 priority = "Low";
		 }
		
		TextView textview;
		
		textview = (TextView) findViewById(R.id.TextViewName);
		textview.setText(taskName);
		textview = (TextView) findViewById(R.id.TextViewDate);
		textview.setText(taskDate);
		textview = (TextView) findViewById(R.id.TextViewTime);
		textview.setText(taskTime);
		textview = (TextView) findViewById(R.id.TextViewPriority);
		textview.setText(priority);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		task = (Task) getIntent().getExtras().getSerializable("TaskObject");
		taskPosition = getIntent().getExtras().getInt("TaskObjectPosition");
		displayTask(task);
		
		ImageButton editButton = (ImageButton) findViewById(R.id.imageButtonEdit);
		editButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DisplayActivity.this, com.example.todolist.EditTaskActivity.class);
				intent.putExtra("TaskObject", task);
				startActivityForResult(intent, MainActivity.edit_request_Code);
			}
		});
		
		ImageButton discardButton = (ImageButton) findViewById(R.id.imageButtonDiscard);
		discardButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("TaskObject", task);
				intent.putExtra("TaskObjectPosition", taskPosition);
				intent.putExtra("Operation", "Discard");
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == MainActivity.edit_request_Code){
			if(resultCode == RESULT_OK){
				Task task = (Task) data.getExtras().getSerializable("TaskObject");
				displayTask(task);
				
				Intent intent = new Intent();
				intent.putExtra("TaskObject", task);
				intent.putExtra("TaskObjectPosition", taskPosition);
				intent.putExtra("Operation", "Edit");
				setResult(RESULT_OK, intent);	
			}
		}
	}
}
