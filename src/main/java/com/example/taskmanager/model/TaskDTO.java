package com.example.taskmanager.model;

import java.util.Objects;

public class TaskDTO {

    private int taskId;

    private int personId;

    private String taskName;

    private String notes;

    private int statusId;

    private String statusName;

    private int priorityId;

    private String priorityName;

    public TaskDTO() {
    }

    public TaskDTO(int taskId, int personId, String taskName, String notes, int statusId, int priorityId) {
        this.taskId = taskId;
        this.personId = personId;
        this.taskName = taskName;
        this.notes = notes;
        this.statusId = statusId;
        this.priorityId = priorityId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return taskId == taskDTO.taskId && personId == taskDTO.personId && statusId == taskDTO.statusId && priorityId == taskDTO.priorityId && Objects.equals(taskName, taskDTO.taskName) && Objects.equals(notes, taskDTO.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, personId, taskName, notes, statusId, priorityId);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskId=" + taskId +
                ", personId=" + personId +
                ", taskName='" + taskName + '\'' +
                ", notes='" + notes + '\'' +
                ", statusId=" + statusId +
                ", priorityId=" + priorityId +
                '}';
    }
}
