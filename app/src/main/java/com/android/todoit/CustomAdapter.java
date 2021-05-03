package com.android.todoit;

import android.content.Context;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<TaskListItem>{

    private List<TaskListItem> dataSet;
    Context mContext;
    private int lastPosition = -1;
    private static class ViewHolder {
        RadioButton done;
        TextView title;
        TextView startDate;
        TextView endDate;
        TextView header;
    }
    public CustomAdapter(List<TaskListItem> data, Context context) {
        super(context, R.layout.list_tasks, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TaskListItem taskItem = getItem(position);
        ViewHolder viewHolder=new ViewHolder();
        final View result;


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            if(!taskItem.isHeader()) {
                convertView = inflater.inflate(R.layout.list_tasks, parent, false);
                viewHolder.title = convertView.findViewById(R.id.taskTitle);
                viewHolder.startDate = convertView.findViewById(R.id.startDate);
                viewHolder.endDate = convertView.findViewById(R.id.endDate);
                viewHolder.done = convertView.findViewById(R.id.done);

            }
            else{
                convertView = inflater.inflate(R.layout.section_header, parent, false);
                viewHolder.header = convertView.findViewById(R.id.header);


            }
            convertView.setTag(viewHolder);

            ;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(viewHolder.header!=null){
            viewHolder.header.setText(taskItem.getGroupName());
            return convertView;
        }
        else {
            final Task task=taskItem.getTask();

            String title=task.getTaskName();
            String startDate=task.getStartTime();
            String endDate=task.getEndTime();
            viewHolder.title.setText(title);
            viewHolder.startDate.setText(startDate);
            viewHolder.endDate.setText(endDate);
            if (task.isDone()) {
                viewHolder.title.setPaintFlags(viewHolder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.startDate.setPaintFlags(viewHolder.startDate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.endDate.setPaintFlags(viewHolder.endDate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            viewHolder.done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RadioButton button = (RadioButton) view;

                    task.setDone(true);
                    notifyDataSetChanged();

                }
            });
            return convertView;
        }

    }
}

