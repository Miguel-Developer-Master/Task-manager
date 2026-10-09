package com.miguel.springbackend.repositories;

import com.miguel.springbackend.ReceiveTask;
import com.miguel.springbackend.Task;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> getAllTasks() {
        String queryTasksSQL = "SELECT * FROM TASKS";
        List<Task> tasks = jdbcTemplate.query(queryTasksSQL, (result, rowNum) -> {
            Task task = new Task();

            task.id = result.getInt("ID");
            task.title = result.getString("TITLE");
            task.description = result.getString("DESCRIPTION");
            task.priority = result.getString("PRIORITY");
            task.completed = result.getBoolean("COMPLETED");

            return task;
        });

        return tasks;
    }

    public Task getTaskById(int id) {
        String getTaskByIdSQL = "SELECT * FROM TASKS WHERE ID = ?";

        Task task = jdbcTemplate.queryForObject(getTaskByIdSQL, (result, rowNum) -> {
            Task selectedTask = new Task();

            selectedTask.title = result.getString("TITLE");
            selectedTask.description = result.getString("DESCRIPTION");
            selectedTask.priority = result.getString("PRIORITY");
            selectedTask.completed = result.getBoolean("COMPLETED");

            return selectedTask;
                        },
                id);

        return task;
    }

    public void deleteTaskById(int id) {
        String deleteTaskByIdSQL = "DELETE FROM TASKS WHERE ID = ?";
        jdbcTemplate.update(deleteTaskByIdSQL, id);
    }

    public void writeTask(ReceiveTask receiveTask) {

        Path dataPath = Path.of("data");
        Path tasks = dataPath.resolve("tasks.db");

        try {

            if (!Files.exists(dataPath)) {
                Files.createDirectory(dataPath);
            }

            if (!Files.exists(tasks)) {
                Files.createFile(tasks);

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sorry, this error occurred while trying to create the database: " + e.getMessage());
        }

        String CREATE_SQL = """
            CREATE TABLE IF NOT EXISTS tasks (
                ID          INTEGER      PRIMARY KEY AUTOINCREMENT,
                TITLE       VARCHAR (30) NOT NULL,
                DESCRIPTION VARCHAR (75),
                PRIORITY    TEXT         NOT NULL,
                COMPLETED   BOOLEAN      DEFAULT (0)
            );
        """;

        jdbcTemplate.execute(CREATE_SQL);

        String writeSQL = """
               INSERT INTO TASKS (TITLE, DESCRIPTION, PRIORITY, COMPLETED)
                VALUES(
                ?,
                ?,
                ?,
                ?)
               """;
        jdbcTemplate.update(writeSQL, receiveTask.title, receiveTask.description, receiveTask.priority, receiveTask.completed);
    }

    public void editTask(int id, ReceiveTask receiveTask) {
        String editTaskSQL = """
                UPDATE TASKS
                SET
                    TITLE = ?,
                    DESCRIPTION = ?,
                    PRIORITY = ?,
                    COMPLETED = ?
                WHERE ID = ?;
        """;

        jdbcTemplate.update(editTaskSQL, receiveTask.title, receiveTask.description, receiveTask.priority, receiveTask.completed, id);
    }
}
