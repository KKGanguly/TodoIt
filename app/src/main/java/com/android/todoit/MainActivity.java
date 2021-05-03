package com.android.todoit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    private EditText taskName;
    private EditText taskDesc;
    private EditText taskStartTime;
    private EditText taskEndTime;
    private EditText date;
    private TextView allTasksLink;
    private DatabaseHelper databaseHelper;
    final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this.getApplicationContext());
        taskName = (EditText) findViewById(R.id.taskName);
        taskDesc = (EditText) findViewById(R.id.taskDescription);
        taskStartTime = (EditText) findViewById(R.id.start);
        taskEndTime = (EditText) findViewById(R.id.end_time);
        date = (EditText) findViewById(R.id.startDate);
        allTasksLink = (TextView) findViewById(R.id.allTasksLink);
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, dateSetListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
                Task task = new Task(taskName.getText().toString(), taskDesc.getText().toString(), taskStartTime.getText().toString(), 0, date.getText().toString(), taskEndTime.getText().toString());
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
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(calendar.getTime()));
    }

}
