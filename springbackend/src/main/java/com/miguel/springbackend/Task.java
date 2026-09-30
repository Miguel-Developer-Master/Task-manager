package com.miguel.springbackend;

public class Task {
    public String title;
    public String description;
    public String priority;
    public boolean completed;
    public int id;

    public Task() {
    }

    public void setValue(ReceiveTask receiveTask, int id) {
        this.title = receiveTask.title;
        this.description = receiveTask.description;
        this.priority = receiveTask.priority;
        this.completed = receiveTask.completed;
        this.id = id;
    }
}
