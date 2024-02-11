package com.example.taskmanager.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskId", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PersonId")
    @JsonBackReference
    private Person person;

    @Nationalized
    @Column(name = "TaskName", length = 50)
    private String taskName;

    @Nationalized
    @Lob
    @Column(name = "Notes")
    private String notes;

//    @ManyToOne
//    @JoinColumn(name = "StatusId")
//    private Status status;
//
//    @ManyToOne
//    @JoinColumn(name = "PriorityId")
//    private Priority priority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public Priority getPriority() {
//        return priority;
//    }
//
//    public void setPriority(Priority priority) {
//        this.priority = priority;
//    }
//
}