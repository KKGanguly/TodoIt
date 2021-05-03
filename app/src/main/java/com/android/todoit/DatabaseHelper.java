package com.android.todoit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "todoit.db";
    public static final String TASK_TABLE_NAME = "task";
    public static final String TASK_COLUMN_ID = "id";
    public static final String TASK_COLUMN_NAME = "taskName";
    public static final String TASK_COLUMN_DESC = "taskDescription";
    public static final String TASK_COLUMN_STARTTME = "startTime";
    public static final String TASK_COLUMN_ENDTIME = "endTime";
    public static final String TASK_COLUMN_ISDONE = "isDone";
    public static final String TASK_COLUMN_DATE = "date";
    public static final String TASK_CREATE_TABLE = "create table " + TASK_TABLE_NAME +
            "(" + TASK_COLUMN_ID + " integer primary key autoincrement," + TASK_COLUMN_NAME + " text," + TASK_COLUMN_DESC + " text," + TASK_COLUMN_ISDONE+" integer default 0,"+TASK_COLUMN_DATE + " text," +TASK_COLUMN_STARTTME + " text," + TASK_COLUMN_ENDTIME + " text)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TASK_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TASK_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_COLUMN_NAME, task.getTaskName());
        contentValues.put(TASK_COLUMN_DESC, task.getTaskDescription());
        contentValues.put(TASK_COLUMN_STARTTME, task.getStartTime());
        contentValues.put(TASK_COLUMN_ISDONE, task.isDone()?1:0);
        contentValues.put(TASK_COLUMN_ENDTIME, task.getEndTime());
        contentValues.put(TASK_COLUMN_DATE, task.getDate());
        db.insert(TASK_TABLE_NAME, null, contentValues);
    }

    public List<Task> getAllTasks(){
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TASK_TABLE_NAME, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(TASK_COLUMN_NAME));
            String desc = cursor.getString(cursor.getColumnIndex(TASK_COLUMN_DESC));
            String start = cursor.getString(cursor.getColumnIndex(TASK_COLUMN_STARTTME));
            int isDone = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_ISDONE));
            String end = cursor.getString(cursor.getColumnIndex(TASK_COLUMN_ENDTIME));
            String date = cursor.getString(cursor.getColumnIndex(TASK_COLUMN_DATE));
            tasks.add(new Task(name, desc, start,isDone, end,date));
        }

        return tasks;

    }

}
