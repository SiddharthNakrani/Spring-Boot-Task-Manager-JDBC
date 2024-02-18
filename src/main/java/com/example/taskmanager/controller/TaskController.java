package com.example.taskmanager.controller;

import com.example.taskmanager.model.TaskDTO;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> tasksById(@PathVariable(value = "id") Integer personId) throws SQLException {

        return taskRepository.getTasksByPersonId(personId);
    }

    @PostMapping(value = "/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO AddTask(@RequestBody TaskDTO newTask) throws SQLException{
        return taskRepository.addTask(newTask);
    }

    @DeleteMapping(value = "/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteTask(@PathVariable(value = "id") Integer id) throws SQLException{
        taskRepository.deleteTaskById(id);
    }
}
