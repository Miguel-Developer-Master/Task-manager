package com.miguel.springbackend;

import com.miguel.springbackend.apis.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
public class JSONController {

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

            tasks.add(task);
            mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), tasks);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, an error occurred. Error: " + e);
        }
    }

    @PostMapping("/tasks/edit")
    public void edit(@RequestParam int id, @RequestBody ReceiveTask receiveTask) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            List<Task> tasks = mapper.readValue(
                    JSONPeopleInfo.JSONqueryPath().toFile(),
                    new TypeReference<List<Task>>() {}
            );

            for (Task task : tasks) {
                if (task.id == id) {

                    task.title = receiveTask.title;
                    task.description = receiveTask.description;
                    task.priority = receiveTask.priority;
                    task.completed = receiveTask.completed;

                }
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    JSONPeopleInfo.JSONqueryPath().toFile(),
                    tasks
            );

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, an error occurred. Error: " + e);
        }
    }
}
