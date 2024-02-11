package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> tasks(@PathVariable(value = "id") Integer personId){
        return taskRepository.findByPersonIdEquals(personId);
    }

    @PostMapping(value = "/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task AddTask(@RequestBody Task newTask){
        taskRepository.save(newTask);
        return newTask;
    }

    @DeleteMapping(value = "/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteTask(@PathVariable(value = "id") Integer id){
        taskRepository.deleteById(id);
    }
}
