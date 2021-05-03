package com.android.todoit;

public class TaskListItem {
    private Task task;
    private boolean isHeader;
    private String groupName;

    public TaskListItem(Task task, boolean isHeader, String groupName) {
        this.task = task;
        this.isHeader = isHeader;
        this.groupName = groupName;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
