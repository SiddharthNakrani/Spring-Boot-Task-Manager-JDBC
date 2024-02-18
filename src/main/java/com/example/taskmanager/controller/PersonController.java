package com.example.taskmanager.controller;


import com.example.taskmanager.model.Credential;
import com.example.taskmanager.model.PersonDTO;

import com.example.taskmanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping(value = "/persons")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> persons(@RequestParam (value = "name", required = false) String name) throws SQLException {
        return personRepository.getAllPersons(name);

    }


    @GetMapping(value = "/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> person(@PathVariable(value = "id") Integer personId) throws SQLException{

        Optional<PersonDTO> selectedPerson = personRepository.getPersonById(personId);

        if(selectedPerson.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found for the given credentials");
        }
        else{
            return ResponseEntity.ok(selectedPerson);
        }
    }



    @PostMapping(value = "/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO AddPerson(@RequestBody PersonDTO person) throws SQLException{
        return personRepository.addPerson(person);
    }

    
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PersonDTO> Login(@RequestBody Credential cred) throws SQLException{
        return personRepository.Login(cred);
    }

    @DeleteMapping(value = "/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeletePerson(@PathVariable(value = "id") Integer id) throws SQLException {
        personRepository.deletePerson(id);
    }



    @PutMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void PersonUpdate(@RequestBody PersonDTO updatedPerson,@PathVariable(value = "id") Integer id) throws SQLException {
        updatedPerson.setPersonId(id);
        personRepository.updatePerson(updatedPerson);
    }

}
