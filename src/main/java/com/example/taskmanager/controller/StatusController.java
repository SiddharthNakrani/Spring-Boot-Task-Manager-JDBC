package com.example.taskmanager.controller;

import com.example.taskmanager.model.StatusDTO;
import com.example.taskmanager.repository.statusRepo.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;


    @GetMapping(value = "/status")
    @ResponseStatus(HttpStatus.OK)
    public List<StatusDTO> status() throws SQLException {
        return statusRepository.GetAllStatuses();
    }

    @GetMapping(value = "/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<StatusDTO> status(@PathVariable(value = "id") Integer statusId) throws SQLException{
        return statusRepository.getStatusById(statusId);
    }
}
