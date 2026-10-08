package com.miguel.springbackend.apis;

import com.miguel.springbackend.Task;
import com.miguel.springbackend.repositories.TaskRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataBasePeopleInfo {

    private final TaskRepository taskRepository;

    public DataBasePeopleInfo(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks/apis/query-data")
    public List<Task> DBquery() {
        return(taskRepository.getAllTasks());
    }

    @GetMapping("/tasks/query-task/{id}")
    public Task DBqueryTask(@PathVariable int id) {
        return(taskRepository.getTaskById(id));
    }

    @DeleteMapping("/tasks/delete/{id}")
    public void deleteTask(@PathVariable int id) {
        taskRepository.deleteTaskById(id);
    }

}