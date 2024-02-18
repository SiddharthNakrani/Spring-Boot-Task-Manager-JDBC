package com.example.taskmanager.repository;

import com.example.taskmanager.model.PriorityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class PriorityRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List<PriorityDTO> getAllPriorities() throws SQLException{

        String sql = "{call dbo.GetAllPriorities()}";

        return jdbcTemplate.query(sql, new PriorityRowMapper());



//        List<PriorityDTO> priorityList = new ArrayList<>();
//
//        try(Connection conn = DriverManager.getConnection(url,user,pwd)){
//            var sql = "{call dbo.GetAllPriorities()}";
//            try (CallableStatement cs = conn.prepareCall(sql)) {
//                var rs = cs.executeQuery();
//
//                while(rs.next()){
//                    var pId = rs.getInt("PriorityId");
//                    var pName = rs.getString("PriorityName");
//
//                    PriorityDTO item = new PriorityDTO(pId, pName);
//
//                    priorityList.add(item);
//                }
//            }
//
//        }
//        return priorityList;
    }


    public Optional<PriorityDTO> getPriorityById(Integer priorityId) throws SQLException{

    String sql = "{call dbo.GetPriorityById(?)}";

    List<PriorityDTO> result = jdbcTemplate.query(sql, new PriorityRowMapper(), priorityId);

    return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));



//        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
//            var sql = "{call dbo.GetPriorityById(?)}";
//            try (CallableStatement cs = conn.prepareCall(sql)) {
//                cs.setInt("priorityId", priorityId);
//                var rs = cs.executeQuery();
//
//                if (rs.next()) {
//
//                    var pId = rs.getInt("PriorityId");
//                    var pName = rs.getString("PriorityName");
//
//                    PriorityDTO selectedPriority = new PriorityDTO(pId, pName);
//                    //System.out.println(id + " " + fname + " " + lname + " " + username + " " + password + " " + age + " " + email + " " + phoneNumber);
//                    return Optional.of(selectedPriority);
//                }
//            }
//        }
//
//
//        return Optional.empty();
    }

    private static class PriorityRowMapper implements RowMapper<PriorityDTO> {
        @Override
        public PriorityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            int priorityId = rs.getInt("PriorityId");
            String priorityName = rs.getString("PriorityName");

            return new PriorityDTO(priorityId, priorityName);
        }
    }

}
