package com.android.todoit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    private EditText taskName;
    private EditText taskDesc;
    private EditText taskStartTime;
    private EditText taskEndTime;
    private TextView allTasksLink;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this.getApplicationContext());
        taskName = (EditText) findViewById(R.id.taskName);
        taskDesc = (EditText) findViewById(R.id.taskDescription);
        taskStartTime = (EditText) findViewById(R.id.start);
        taskEndTime = (EditText) findViewById(R.id.end_time);

        allTasksLink = (TextView) findViewById(R.id.allTasksLink);

        taskStartTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        taskStartTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        taskEndTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        taskEndTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        Button submitTask = (Button) findViewById(R.id.addTask);
        submitTask.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task(taskName.getText().toString(), taskDesc.getText().toString(), taskStartTime.getText().toString(), taskEndTime.getText().toString());
                databaseHelper.insertTask(task);
                Intent switchActivityIntent =new  Intent(MainActivity.this, TaskViewActivity.class);
                startActivity(switchActivityIntent);
            }
        });

        allTasksLink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent =new  Intent(MainActivity.this, TaskViewActivity.class);
                startActivity(switchActivityIntent);
            }
        });
    }

}
