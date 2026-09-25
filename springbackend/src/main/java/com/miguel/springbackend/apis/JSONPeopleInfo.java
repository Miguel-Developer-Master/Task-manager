package com.miguel.springbackend.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JSONPeopleInfo {


    public static Path JSONqueryPath() throws IOException {
        Path jsonFile = Path.of("data").resolve(Path.of("people.json"));

        return jsonFile;
    }

    public static List<String> JSONquery() throws IOException {
        Path jsonFile = Path.of("data").resolve(Path.of("people.json"));
        List<String> json = Files.readAllLines(jsonFile);


        return json;
    }

    @GetMapping("/tasks/apis/query-data")
    public List<String> JSONqueryExport() {
        try {
            List<String> json = JSONquery();

            return json;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static int JSONquerySize(int x) throws IOException {
        if (x == 1) {
            return JSONquery().size(); //real size
        } else {
            return JSONquery().size() - 1; // index size
        }
    }

    public static List<String> JSONqueryTask(int id) throws IOException {
        Path jsonFile = Path.of("data").resolve(Path.of("people.json"));
        List<String> json = JSONquery();
        List<String> task = new ArrayList<>();


        for (int i = 0; i < json.size(); i++) {
            String line = json.get(i);

            if (line.equals("\"id\": " + id)) {
                task.add(0, json.get(i - 1));

                for (int a = 0; a < 6; a++) {

                    if (a == 0) {
                        String adding = json.get(i);
                        task.add(adding);
                    } else {
                        String adding = json.get(i + a);
                        task.add(adding);
                    }

                }
            break;
            }

        }

        if (task.isEmpty()) {
            throw new RuntimeException("Sorry, the task wasn't found");
        } else {
            return task;
        }
    }

    @GetMapping("/tasks/apis/query-tasks-info")
    public String JSONqueryInfo() {

        try {

            int counter = JSONCounter.counterSizeQuery(1);
            int firstBracketCounter = 0;
            int lastBracketCounter = 0;
            List<String> json = JSONquery();

            for (int i = 0; i < json.size(); i++) {
                String line = json.get(i);

                if (line.trim().equals("[")) {
                    firstBracketCounter = i;
                } else if (line.trim().equals("]")) {
                    lastBracketCounter = i;
                }
            }

            String text = firstBracketCounter + "," + counter + "," +lastBracketCounter;
            return(text);

            /*
            first number: first curly bracket
            second number: Number of tasks
            third number: last curly bracket
             */

        } catch (IOException e) {
            throw new RuntimeException("Sorry, this error ocurred: " + e);
        }

    }

}