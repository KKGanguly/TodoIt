package com.android.todoit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class TaskViewActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        databaseHelper = new DatabaseHelper(this.getApplicationContext());
        listView=(ListView)findViewById(R.id.listTasks);
        List<Task> tasks = databaseHelper.getAllTasks();
        listView.setAdapter(new CustomAdapter(tasks,this.getApplicationContext()));
    }
}