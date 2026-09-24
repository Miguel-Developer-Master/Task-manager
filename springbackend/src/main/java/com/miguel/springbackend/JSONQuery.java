package com.miguel.springbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.miguel.springbackend.apis.JSONPeopleInfo;

import java.io.IOException;
import java.util.List;

@RestController
public class JSONQuery {

    @GetMapping("/tasks/query-task/{id}")
    public List<String> getTask(@PathVariable int id) {
        try {
           List<String> task = JSONPeopleInfo.JSONqueryTask(id);

           return task;
        } catch (IOException e) {
            throw new RuntimeException("Sorry, an error occurred. Error: " + e);
        }
    }
}
