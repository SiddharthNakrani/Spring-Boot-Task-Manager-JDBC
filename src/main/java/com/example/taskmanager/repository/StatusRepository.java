package com.example.taskmanager.repository;

import com.example.taskmanager.model.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class StatusRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<StatusDTO> GetAllStatuses() throws SQLException{

        String sql = "{call dbo.GetAllStatuses()}";

        return jdbcTemplate.query(sql, new StatusRowMapper());


//        List<StatusDTO> statusDTOList = new ArrayList<>();
//
//        try(Connection conn = DriverManager.getConnection(url,user,pwd)){
//            var sql = "{call dbo.GetAllStatuses()}";
//
//            try (CallableStatement cs = conn.prepareCall(sql)) {
//                var rs = cs.executeQuery();
//
//                while(rs.next()){
//                    var sId = rs.getInt("StatusId");
//                    var sName = rs.getString("StatusName");
//
//                    StatusDTO selectedStatus = new StatusDTO(sId, sName);
//
//                    statusDTOList.add(selectedStatus);
//
//                }
//            }
//        }
//        return statusDTOList;
    }


    public Optional<StatusDTO> getStatusById(Integer statusId) throws SQLException{

        String sql = "{call dbo.GetStatusById(?)}";

        List<StatusDTO> result = jdbcTemplate.query(sql, new Object[]{statusId}, new StatusRowMapper());

        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));


//        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
//            var sql = "{call dbo.GetStatusById(?)}";
//
//            try (CallableStatement cs = conn.prepareCall(sql)) {
//                cs.setInt("StatusId", statusId);
//                var rs = cs.executeQuery();
//
//                if (rs.next()) {
//
//                    var sId = rs.getInt("StatusId");
//                    var sName = rs.getString("StatusName");
//
//                    StatusDTO selectedStatus = new StatusDTO(sId, sName);
//
//                    return Optional.of(selectedStatus);
//                }
//            }
//        }
//        return Optional.empty();
    }

    private static class StatusRowMapper implements RowMapper<StatusDTO> {
        @Override
        public StatusDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            int statusId = rs.getInt("StatusId");
            String statusName = rs.getString("StatusName");

            return new StatusDTO(statusId, statusName);
        }
    }

}
