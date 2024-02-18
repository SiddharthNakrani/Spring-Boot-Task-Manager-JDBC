package com.example.taskmanager.model;

import java.util.Objects;
import java.util.List;

public class PersonDTO {

    private Integer personId;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private Integer age;

    private String email;

    private String phoneNumber;

    private List<TaskDTO> tasks;

    public PersonDTO(){

    }
    public PersonDTO(Integer personId, String firstName, String lastName, String userName, String password, Integer age, String email, String phoneNumber) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(personId, personDTO.personId) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(userName, personDTO.userName) && Objects.equals(password, personDTO.password) && Objects.equals(age, personDTO.age) && Objects.equals(email, personDTO.email) && Objects.equals(phoneNumber, personDTO.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, userName, password, age, email, phoneNumber);
    }

}
