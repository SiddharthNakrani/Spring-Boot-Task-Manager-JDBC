package com.example.taskmanager.model;

public class StatusDTO {

    private int statusId;

    private String statusName;

    public StatusDTO() {
    }

    public StatusDTO(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
