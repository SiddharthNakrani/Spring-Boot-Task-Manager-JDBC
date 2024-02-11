package com.example.taskmanager.repository.statusRepo;

import com.example.taskmanager.model.StatusDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StatusRepository {

    List<StatusDTO> GetAllStatuses() throws SQLException;

    Optional<StatusDTO> getStatusById(Integer statusId) throws SQLException;
}
