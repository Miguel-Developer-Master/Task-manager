package com.miguel.springbackend;

import com.miguel.springbackend.apis.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
public class JSONWritter {

    @PostMapping("/tasks/write")
    public void write(@RequestBody ReceiveTask receiveTask) {
        try {

            JSONCreator.create();
            JSONCounter.counterCreate();

            Task task = new Task();
            task.setValue(receiveTask, Integer.parseInt(JSONCounter.counterQueryLast()));
            
            Path path = JSONPeopleInfo.JSONqueryPath();

            ObjectMapper mapper = new ObjectMapper();


            List<Task> tasks = mapper.readValue(path.toFile(), new TypeReference<List<Task>>() {});
            System.out.println("Path: " + path);
            System.out.println("Tasks: " + tasks);
            System.out.println("Task count: " + tasks.size());

            tasks.add(task);
            mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), tasks);
            System.out.println("WRITE FINISHED");

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, an error occurred. Error: " + e);
        }
    }
}
