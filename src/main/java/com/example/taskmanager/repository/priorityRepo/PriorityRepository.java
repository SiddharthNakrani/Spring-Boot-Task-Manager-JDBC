package com.example.taskmanager.repository.priorityRepo;

import com.example.taskmanager.model.PriorityDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PriorityRepository {

    List<PriorityDTO> getAllPriorities() throws SQLException;

    Optional<PriorityDTO> getPriorityById(Integer priorityId) throws SQLException;
}
