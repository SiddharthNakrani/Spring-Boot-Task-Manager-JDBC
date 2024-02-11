package com.example.taskmanager.model;

public class PriorityDTO {

    private int id;


    private String priorityName;

    public PriorityDTO() {
    }

    public PriorityDTO(int id, String priorityName) {
        this.id = id;
        this.priorityName = priorityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }
}
