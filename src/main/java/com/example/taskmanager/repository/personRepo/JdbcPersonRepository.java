package com.example.taskmanager.repository.personRepo;

import com.example.taskmanager.model.PersonDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcPersonRepository implements PersonRepository {

    String url = "jdbc:sqlserver://LAPTOP-CSP0KH0U\\SQLEXPRESS;encrypt=true;trustServerCertificate=true;databaseName=TaskManager";
    String user = "siddharthdb";
    String pwd = "magnic2020@#";

    @Override
    public List<PersonDTO> getAllPersons() throws SQLException {
        List<PersonDTO> personList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
            var sql = "{call dbo.GetAllPersons()}";
            try (CallableStatement cs = conn.prepareCall(sql)) {
                var rs = cs.executeQuery();

                while (rs.next()) {

                    var id = rs.getInt("PersonId");
                    var fname = rs.getString("FirstName");
                    var lname = rs.getString("LastName");
                    var username = rs.getString("Username");
                    var password = rs.getString("Password");
                    var age = rs.getInt("Age");
                    var email = rs.getString("Email");
                    var phoneNumber = rs.getString("PhoneNumber");

                    PersonDTO p = new PersonDTO(id, fname, lname, username, password, age, email, phoneNumber);
                    personList.add(p);
                    //System.out.println(id + " " + fname + " " + lname + " " + username + " " + password + " " + age + " " + email + " " + phoneNumber);

                }
            }
        }

        return personList;
    }

    @Override
    public Optional<PersonDTO> getPersonById(Integer personId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
            var sql = "{call dbo.GetPersonById(?)}";
            try (CallableStatement cs = conn.prepareCall(sql)) {
                cs.setInt("personId", personId);
                var rs = cs.executeQuery();

                if (rs.next()) {

                    var id = rs.getInt("PersonId");

                    var fname = rs.getString("FirstName");
                    var lname = rs.getString("LastName");
                    var username = rs.getString("Username");
                    var password = rs.getString("Password");
                    var age = rs.getInt("Age");
                    var email = rs.getString("Email");
                    var phoneNumber = rs.getString("PhoneNumber");

                    PersonDTO selectedPerson = new PersonDTO(id, fname, lname, username, password, age, email, phoneNumber);
                    //System.out.println(id + " " + fname + " " + lname + " " + username + " " + password + " " + age + " " + email + " " + phoneNumber);
                    return Optional.of(selectedPerson);
                }
            }
        }

        return Optional.empty();
    }


    @Override
    public PersonDTO addPerson(PersonDTO person) throws SQLException{

        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
            var sql = "{call dbo.AddPerson(?,?,?,?,?,?,?,?)}";

            try (CallableStatement cs = conn.prepareCall(sql)) {

                cs.setString("firstName", person.getFirstName());
                cs.setString("lastName", person.getLastName());
                cs.setString("userName", person.getUserName());
                cs.setString("password", person.getPassword());
                cs.setInt("age", person.getAge());
                cs.setString("email", person.getEmail());
                cs.setString("phoneNumber", person.getPhoneNumber());

                cs.registerOutParameter("generatedValue", Types.INTEGER );

                cs.executeUpdate();


                int primaryKey = cs.getInt("generatedValue");


                person.setPersonId(primaryKey);

                return person;


            }
        }
    }



}
