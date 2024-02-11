package com.example.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersonId", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Nationalized
    @Column(name = "LastName", length = 50)
    private String lastName;

    @Nationalized
    @Column(name = "Username", length = 50)
    private String username;

    @Nationalized
    @Column(name = "Password", length = 50)
    private String password;

    @Column(name = "Age")
    private Integer age;

    @Nationalized
    @Column(name = "Email", length = 321)
    private String email;

    @Nationalized
    @Column(name = "PhoneNumber", length = 25)
    private String phoneNumber;

    @OneToMany(mappedBy = "person")
    @JsonManagedReference
    private List<Task> tasks;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


}