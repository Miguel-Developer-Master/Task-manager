package com.miguel.springbackend;

import com.miguel.springbackend.apis.*;
import com.miguel.springbackend.repositories.TaskRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class DataBaseController {

    private final TaskRepository taskRepository;

    public DataBaseController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/tasks/write")
    public void write(@RequestBody ReceiveTask receiveTask) {
        taskRepository.writeTask(receiveTask);
    }

    @PostMapping("/tasks/edit")
    public void edit(@RequestParam int id, @RequestBody ReceiveTask receiveTask) {
        taskRepository.editTask(id, receiveTask);
    }
}
