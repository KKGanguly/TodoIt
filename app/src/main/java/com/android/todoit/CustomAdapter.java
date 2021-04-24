package com.android.todoit;

import android.content.Context;

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

public class CustomAdapter extends ArrayAdapter<Task>{

    private List<Task> dataSet;
    Context mContext;
    private int lastPosition = -1;
    private static class ViewHolder {
        RadioButton done;
        TextView title;
        TextView startDate;
        TextView endDate;
    }

    public CustomAdapter(List<Task> data, Context context) {
        super(context, R.layout.list_tasks, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_tasks, parent, false);
            viewHolder.title =  convertView.findViewById(R.id.taskTitle);
            viewHolder.startDate = convertView.findViewById(R.id.startDate);
            viewHolder.endDate = convertView.findViewById(R.id.endDate);
            viewHolder.done = convertView.findViewById(R.id.done);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        String title=task.getTaskName();
        String startDate=task.getStartTime();
        String endDate=task.getEndTime();
        viewHolder.title.setText(title);
        viewHolder.startDate.setText(startDate);
        viewHolder.endDate.setText(endDate);
        return convertView;
    }
}

