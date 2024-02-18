package com.example.taskmanager.repository;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.model.Credential;
import com.example.taskmanager.model.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskRepository taskRepository;


    public List<PersonDTO> getAllPersons(String name) throws SQLException {

        String qSql = "{call dbo.GetAllPersons(?)}";

        return jdbcTemplate.query(qSql, new PersonRowMapper(taskRepository), name);

//        List<PersonDTO> personList = new ArrayList<>();
//
//        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
//            var sql = "{call dbo.GetAllPersons()}";
//            try (CallableStatement cs = conn.prepareCall(sql)) {
//                var rs = cs.executeQuery();
//
//                while (rs.next()) {
//
//                    var id = rs.getInt("PersonId");
//                    var fname = rs.getString("FirstName");
//                    var lname = rs.getString("LastName");
//                    var username = rs.getString("Username");
//                    var password = rs.getString("Password");
//                    var age = rs.getInt("Age");
//                    var email = rs.getString("Email");
//                    var phoneNumber = rs.getString("PhoneNumber");
//
//                    PersonDTO p = new PersonDTO(id, fname, lname, username, password, age, email, phoneNumber);
//                    personList.add(p);
//                    //System.out.println(id + " " + fname + " " + lname + " " + username + " " + password + " " + age + " " + email + " " + phoneNumber);
//
//                }
//            }
//        }
//
//        return personList;
    }


    public Optional<PersonDTO> getPersonById(Integer personId) throws SQLException {

        var sql = "{call dbo.GetPersonById(?)}";

        List<PersonDTO> result = jdbcTemplate.query(sql, new PersonRowMapper(taskRepository), personId);

        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));




//        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
//            var sql = "{call dbo.GetPersonById(?)}";
//            try (CallableStatement cs = conn.prepareCall(sql)) {
//                cs.setInt("personId", personId);
//                var rs = cs.executeQuery();
//
//                if (rs.next()) {
//
//                    var id = rs.getInt("PersonId");
//
//                    var fname = rs.getString("FirstName");
//                    var lname = rs.getString("LastName");
//                    var username = rs.getString("Username");
//                    var password = rs.getString("Password");
//                    var age = rs.getInt("Age");
//                    var email = rs.getString("Email");
//                    var phoneNumber = rs.getString("PhoneNumber");
//
//                    PersonDTO selectedPerson = new PersonDTO(id, fname, lname, username, password, age, email, phoneNumber);
//                    //System.out.println(id + " " + fname + " " + lname + " " + username + " " + password + " " + age + " " + email + " " + phoneNumber);
//                    return Optional.of(selectedPerson);
//                }
//            }
//
//        }
//
//        return Optional.empty();
    }


    public PersonDTO addPerson(PersonDTO person) throws SQLException{

        var sql = "{call dbo.AddPerson(?,?,?,?,?,?,?,?)}";

        jdbcTemplate.update(sql, person.getFirstName(),person.getLastName(),person.getUserName(), person.getPassword(),
                person.getAge(),person.getEmail(),person.getPhoneNumber(), Types.INTEGER);


        return person;



//        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
//            var sql = "{call dbo.AddPerson(?,?,?,?,?,?,?,?)}";
//
//            try (CallableStatement cs = conn.prepareCall(sql)) {
//
//                cs.setString("firstName", person.getFirstName());
//                cs.setString("lastName", person.getLastName());
//                cs.setString("userName", person.getUserName());
//                cs.setString("password", person.getPassword());
//                cs.setInt("age", person.getAge());
//                cs.setString("email", person.getEmail());
//                cs.setString("phoneNumber", person.getPhoneNumber());
//
//                cs.registerOutParameter("generatedValue", Types.INTEGER );
//
//                cs.executeUpdate();
//
//
//                int primaryKey = cs.getInt("generatedValue");
//
//
//                person.setPersonId(primaryKey);
//
//                return person;
//
//
//            }
//        }
    }

    public void deletePerson(Integer personId) throws SQLException{
        var sql = "{call dbo.DeletePerson(?)}";

        jdbcTemplate.update(sql, personId);

    }

    public void updatePerson(PersonDTO person) throws SQLException{
        var sql = "{call dbo.UpdatePerson(?,?,?,?,?,?,?,?)}";

        jdbcTemplate.update(sql, person.getPersonId() ,person.getFirstName(),person.getLastName(),person.getUserName(),
                person.getPassword(), person.getAge(),person.getEmail(),person.getPhoneNumber());

    }

    public Optional<PersonDTO> Login(Credential cred) throws SQLException{
        var sql = "{call dbo.Login(?,?)}";

        List<PersonDTO> result = jdbcTemplate.query(sql, new PersonRowMapper(taskRepository), cred.username(), cred.password());

        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }





    private class PersonRowMapper implements RowMapper<PersonDTO> {
        private final TaskRepository taskRepository;


        public PersonRowMapper(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        @Override
        public PersonDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("PersonId");
            String fname = rs.getString("FirstName");
            String lname = rs.getString("LastName");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            int age = rs.getInt("Age");
            String email = rs.getString("Email");
            String phoneNumber = rs.getString("PhoneNumber");

            PersonDTO newPerson = new PersonDTO(id, fname, lname, username, password, age, email, phoneNumber);

            newPerson.setTasks(taskRepository.getTasksByPersonId(id));

            return newPerson;
        }
    }


}
