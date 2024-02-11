package com.example.taskmanager.repository.priorityRepo;

import com.example.taskmanager.model.PriorityDTO;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcPriorityRepository implements PriorityRepository{

    String url = "jdbc:sqlserver://LAPTOP-CSP0KH0U\\SQLEXPRESS;encrypt=true;trustServerCertificate=true;databaseName=TaskManager";
    String user = "siddharthdb";
    String pwd = "magnic2020@#";

    @Override
    public List<PriorityDTO> getAllPriorities() throws SQLException{

        List<PriorityDTO> priorityList = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,user,pwd)){
            var sql = "{call dbo.GetAllPriorities()}";
            try (CallableStatement cs = conn.prepareCall(sql)) {
                var rs = cs.executeQuery();

                while(rs.next()){
                    var pId = rs.getInt("PriorityId");
                    var pName = rs.getString("PriorityName");

                    PriorityDTO item = new PriorityDTO(pId, pName);

                    priorityList.add(item);
                }
            }

        }
        return priorityList;
    }


    public Optional<PriorityDTO> getPriorityById(Integer priorityId) throws SQLException{

        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
            var sql = "{call dbo.GetPriorityById(?)}";
            try (CallableStatement cs = conn.prepareCall(sql)) {
                cs.setInt("priorityId", priorityId);
                var rs = cs.executeQuery();

                if (rs.next()) {

                    var pId = rs.getInt("PriorityId");
                    var pName = rs.getString("PriorityName");

                    PriorityDTO selectedPriority = new PriorityDTO(pId, pName);
                    //System.out.println(id + " " + fname + " " + lname + " " + username + " " + password + " " + age + " " + email + " " + phoneNumber);
                    return Optional.of(selectedPriority);
                }
            }
        }


        return Optional.empty();
    }



}
