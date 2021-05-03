package com.android.todoit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        List<TaskListItem> taskListItems=new ArrayList<>();
        TaskListItem taskListItem=new TaskListItem(null, true, "test");
        taskListItems.add(taskListItem);
        for (Task task: tasks){
            taskListItem=new TaskListItem(task, false, "");
            taskListItems.add(taskListItem);
        }
        listView.setAdapter(new CustomAdapter(taskListItems,this.getApplicationContext()));
    }
    private List<TaskListItem> getGroups(List<Task> tasks){
        TaskListItem taskListItem;
        for (Task task: tasks){
            taskListItem=new TaskListItem(task, false, "");
            Date date= getStringToDate(task.getDate());
            
        }
    }

    private Date getStringToDate(String dateText){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date date = format.parse(dateText);
            System.out.println(date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
