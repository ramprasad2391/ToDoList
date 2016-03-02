//Group9A_HW02
//Ram Prasad Narayanaswamy
//Aaron Maisto


package com.example.todolist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    ImageButton addTaskBtn;
    ScrollView scrollView;
    TextView textViewNoOfTasks;
    String mainTitle;
    LinearLayout linearLayout;
    LinkedList<Task> taskList;
    public final static int create_request_Code = 100;
    public final static int display_request_Code = 101;
    public final static int edit_request_Code = 102;
	
	
    public void createTaskList(Task task,int position){
    	
    	textViewNoOfTasks.setText(taskList.size()+" ");
    	textViewNoOfTasks.append(mainTitle);
    	
    	LinearLayout ll = new LinearLayout(MainActivity.this);
		ll.setOrientation(LinearLayout.VERTICAL);		
		ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		ll.setWeightSum((float) 1.0);
		ll.setPadding(2,2,2,12);
		ll.setTag(R.id.task_list_position,position);
		linearLayout.addView(ll);
		
		TextView tv1 = new TextView(MainActivity.this);
		tv1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tv1.setTextAppearance(MainActivity.this, android.R.style.TextAppearance_Large);
		tv1.setTextSize((float) 40.0);
		tv1.setText(task.getName());
		tv1.setClickable(true);
		tv1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*int clicked_task_pos = ((Integer)((LinearLayout)v.getParent()).getTag(R.id.task_list_position)).intValue();
				Task clicked_task = taskList.get(clicked_task_pos);*/
				
				int clicked_task_pos = linearLayout.indexOfChild((View)v.getParent());
				Log.d("position....", clicked_task_pos+"");
				Task clicked_task = taskList.get(clicked_task_pos);
				Log.d("Task.....", clicked_task.toString());
				
				Intent intent = new Intent(MainActivity.this, com.example.todolist.DisplayActivity.class);
				intent.putExtra("TaskObjectPosition", clicked_task_pos);
				intent.putExtra("TaskObject", clicked_task);
				startActivityForResult(intent, display_request_Code);
			}
		});
		ll.addView(tv1);
		
		TextView tv2 = new TextView(MainActivity.this);
		tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tv2.setTextAppearance(MainActivity.this, android.R.style.TextAppearance_Medium);
		tv2.setTextSize((float) 20.0);
		tv2.setPadding(2, 0, 0, 0);
		tv2.setText(task.getDate());
		ll.addView(tv2);
		
		TextView tv3 = new TextView(MainActivity.this);
		tv3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tv3.setTextAppearance(MainActivity.this, android.R.style.TextAppearance_Medium);
		tv3.setText(task.getTime());
		tv3.setPadding(2, 0, 0, 0);
		tv3.setTextSize((float) 20.0);
		ll.addView(tv3);
    }
    
    
    
    public void editTaskList(Task task,int position){
    	LinearLayout ll = (LinearLayout) linearLayout.getChildAt(position);
    	
    	Log.d("demo", ((TextView) ll.getChildAt(0)).getText()+"");
    	((TextView) ll.getChildAt(0)).setText(task.getName());
    	((TextView) ll.getChildAt(1)).setText(task.getDate());
    	((TextView) ll.getChildAt(2)).setText(task.getTime()); 
    	
    	Log.d("demo", ll.getChildCount()+"");
    }
    
    
    public void addTasks(Task task, int position){
    	taskList.add(task);
    	createTaskList(task,position); 
	}
    
    
    
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskList = new LinkedList<Task>();
        
        textViewNoOfTasks = (TextView) findViewById(R.id.textViewNoOfTasks);
        mainTitle = getResources().getString(R.string.no_of_tasks);
        
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        linearLayout = (LinearLayout) scrollView.findViewById(R.id.taskLayout);
        
        addTaskBtn = (ImageButton) findViewById(R.id.imageButtonAddTask);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, com.example.todolist.CreateTaskActivity.class);
				startActivityForResult(intent, create_request_Code);
			}
		});
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == MainActivity.create_request_Code){
			if(resultCode == RESULT_OK){
				Task task = (Task) data.getExtras().getSerializable("TaskObject");
				int position = taskList.size();				
				addTasks(task,position);
			}
		}
		
		if(requestCode == MainActivity.display_request_Code){
			if(resultCode == RESULT_OK){
				Task task = (Task) data.getExtras().getSerializable("TaskObject");
				int taskPosition = data.getExtras().getInt("TaskObjectPosition");
				String operation = data.getExtras().getString("Operation");
				if(operation.equals("Discard")){					
					taskList.remove(taskPosition);
					linearLayout.removeViewAt(taskPosition);
					textViewNoOfTasks.setText(taskList.size()+" ");
			    	textViewNoOfTasks.append(mainTitle);	
				}
				else{
					Log.d("tasklist",taskList.toString());
					Log.d("demo check", taskPosition+"");
					taskList.set(taskPosition, task);
					editTaskList(task, taskPosition);
				}	
			}
		}
	}    
}
