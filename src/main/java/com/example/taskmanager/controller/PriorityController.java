package com.example.taskmanager.controller;

import com.example.taskmanager.model.PriorityDTO;
import com.example.taskmanager.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class PriorityController {


    @Autowired
    private PriorityRepository priorityRepository;

    @GetMapping(value = "/priority")
    @ResponseStatus(HttpStatus.OK)
    public List<PriorityDTO> priorities() throws SQLException {
        return priorityRepository.getAllPriorities();
    }

    @GetMapping(value = "/priority/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PriorityDTO> priority(@PathVariable(value = "id") Integer priorityId) throws SQLException {
        return priorityRepository.getPriorityById(priorityId);
    }


}
