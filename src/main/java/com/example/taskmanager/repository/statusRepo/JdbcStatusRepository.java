package com.example.taskmanager.repository.statusRepo;

import com.example.taskmanager.model.StatusDTO;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcStatusRepository implements StatusRepository{

    String url = "jdbc:sqlserver://LAPTOP-CSP0KH0U\\SQLEXPRESS;encrypt=true;trustServerCertificate=true;databaseName=TaskManager";
    String user = "siddharthdb";
    String pwd = "magnic2020@#";


    public List<StatusDTO> GetAllStatuses() throws SQLException{

        List<StatusDTO> statusDTOList = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,user,pwd)){
            var sql = "{call dbo.GetAllStatuses()}";

            try (CallableStatement cs = conn.prepareCall(sql)) {
                var rs = cs.executeQuery();

                while(rs.next()){
                    var sId = rs.getInt("StatusId");
                    var sName = rs.getString("StatusName");

                    StatusDTO selectedStatus = new StatusDTO(sId, sName);

                    statusDTOList.add(selectedStatus);

                }
            }
        }
        return statusDTOList;
    }


    public Optional<StatusDTO> getStatusById(Integer statusId) throws SQLException{

        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
            var sql = "{call dbo.GetStatusById(?)}";

            try (CallableStatement cs = conn.prepareCall(sql)) {
                cs.setInt("StatusId", statusId);
                var rs = cs.executeQuery();

                if (rs.next()) {

                    var sId = rs.getInt("StatusId");
                    var sName = rs.getString("StatusName");

                    StatusDTO selectedStatus = new StatusDTO(sId, sName);

                    return Optional.of(selectedStatus);
                }
            }
        }
        return Optional.empty();
    }

}
