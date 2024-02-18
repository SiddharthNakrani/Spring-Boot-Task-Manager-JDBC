package com.example.taskmanager.repository;



import com.example.taskmanager.model.PersonDTO;
import com.example.taskmanager.model.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TaskDTO> getTasksByPersonId(int personId) throws SQLException{
        String sql = "{call dbo.GetTasksByPersonId(?)}";

        return jdbcTemplate.query(sql, new Object[]{personId}, new TaskRowMapper());
    }

    public TaskDTO addTask(TaskDTO newTask) throws SQLException{

        var sql = "{call dbo.AddTask(?,?,?,?,?)}";

        jdbcTemplate.update(sql, newTask.getPersonId(), newTask.getTaskName(), newTask.getNotes(),
                newTask.getStatusId(), newTask.getPriorityId());

        return newTask;

    }

    public void deleteTaskById(Integer taskid) throws SQLException{
        var sql = "{call dbo.DeleteTask(?)}";

        jdbcTemplate.update(sql, taskid);
    }

    private static class TaskRowMapper implements RowMapper<TaskDTO> {
        @Override
        public TaskDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTaskId(rs.getInt("TaskId"));
            taskDTO.setPersonId(rs.getInt("PersonId"));
            taskDTO.setTaskName(rs.getString("TaskName"));
            taskDTO.setNotes(rs.getString("Notes"));
            taskDTO.setStatusId(rs.getInt("StatusId"));
            taskDTO.setStatusName(rs.getString("StatusName"));
            taskDTO.setPriorityId(rs.getInt("PriorityId"));
            taskDTO.setPriorityName(rs.getString("PriorityName"));

            return taskDTO;
        }



    }





}