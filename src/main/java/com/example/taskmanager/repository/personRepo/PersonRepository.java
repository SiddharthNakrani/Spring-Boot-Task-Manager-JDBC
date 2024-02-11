package com.example.taskmanager.repository.personRepo;

import com.example.taskmanager.model.PersonDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<PersonDTO> getAllPersons() throws SQLException;
    Optional<PersonDTO> getPersonById(Integer personId) throws SQLException;
    PersonDTO addPerson(PersonDTO person) throws SQLException;

}
