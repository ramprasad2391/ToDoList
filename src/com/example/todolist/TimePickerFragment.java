//Group9A_HW02
//Ram Prasad Narayanaswamy
//Aaron Maisto

package com.example.todolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;



public class TimePickerFragment extends DialogFragment  {
	Calendar calendarDate;
	Calendar calendarTime;
	static EditText editTextDate;
	static EditText editTextTime;
	static Activity activity;
	
	public void dateTimePickerDialogDisplay(Activity activity, EditText editTextDate, EditText editTextTime){
		
		TimePickerFragment.editTextDate = editTextDate;
		TimePickerFragment.editTextTime = editTextTime;
		TimePickerFragment.activity = activity;
		
		if(TimePickerFragment.editTextDate.getText() == null || TimePickerFragment.editTextDate.getText().toString().length() == 0){
			calendarDate = Calendar.getInstance(Locale.US);
			
		}
		else{
			String date = editTextDate.getText().toString();
			Log.d("demo", date);
			
			String dateFormat = "MM/dd/yy";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,Locale.US);
			Date dt;
			try {
				dt = sdf.parse(date);
				calendarDate = Calendar.getInstance();
				calendarDate.setTime(dt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
		if(TimePickerFragment.editTextTime.getText() == null || TimePickerFragment.editTextTime.getText().toString().length() == 0){
			calendarTime = Calendar.getInstance();
		}
		else{
			String time = editTextTime.getText().toString();
			Log.d("demo", time);
			
			String timeFormat = "hh:mm a";
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.US);
			Date dt;
			try {
				dt = sdf.parse(time);
				calendarTime = Calendar.getInstance();
				calendarTime.setTime(dt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				calendarDate.set(Calendar.YEAR, year);
				calendarDate.set(Calendar.MONTH, monthOfYear);
				calendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateDate(TimePickerFragment.editTextDate);
			}
		};
		
		editTextDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(TimePickerFragment.activity, date,
						calendarDate.get(Calendar.YEAR), calendarDate
								.get(Calendar.MONTH), calendarDate
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		editTextDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
				new DatePickerDialog(TimePickerFragment.activity, date,
						calendarDate.get(Calendar.YEAR), calendarDate
								.get(Calendar.MONTH), calendarDate
								.get(Calendar.DAY_OF_MONTH)).show();
			}}
		});
		
		
		final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				calendarTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendarTime.set(Calendar.MINUTE, minute);
				updateTime(TimePickerFragment.editTextTime);
			}
		};

		editTextTime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				new TimePickerDialog(TimePickerFragment.activity, time,
						calendarTime.get(Calendar.HOUR_OF_DAY), calendarTime
								.get(Calendar.MINUTE), false).show();
			}
		});
		
		editTextTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
				new TimePickerDialog(TimePickerFragment.activity, time,
						calendarTime.get(Calendar.HOUR_OF_DAY), calendarTime
								.get(Calendar.MINUTE), false).show();
			}}
		});
		
	}
	private void updateDate(EditText editTextDate) {
		String dateFormat = "MM/dd/yy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
		editTextDate.setText(sdf.format(calendarDate.getTime()));
	}
	
	private void updateTime(EditText editTextTime) {
		// TODO Auto-generated method stub
		String timeFormat = "hh:mm";
	    SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.US);
		editTextTime.setText(sdf.format(calendarTime.getTime()));
		String am_pm = (calendarTime.get(Calendar.HOUR_OF_DAY) < 12) ? "AM" : "PM";
		editTextTime.append(" "+am_pm);
		
	}
	
}
