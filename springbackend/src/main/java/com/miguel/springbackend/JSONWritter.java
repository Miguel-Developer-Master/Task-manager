package com.miguel.springbackend;

import com.miguel.springbackend.apis.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
public class JSONWritter {

    @PostMapping("/tasks/write")
    public void write(@RequestBody Task task) {
        try {

            JSONCreator.createJSON();
            JSONCounter.counterCreate();

            String id = JSONCounter.counterQueryLast();

            List<String> peopleData = JSONPeopleInfo.JSONquery();

            int lastKey = 0;
            String toWriteLastKey = "";
            for (int i = 0; i < peopleData.size(); i++) {
                String line = peopleData.get(i);

                if (line.trim().equals("}")) {
                    lastKey = i;
                    toWriteLastKey = line;
                }
            }

            Path peopleDataFile = JSONPeopleInfo.JSONqueryPath();

            String comma = ",";
            String startKey = "  {";
            String firstHalfBody = "    \"id\": " + id + "\n    \"title\": " + task.title;
            String secondHalfBody = firstHalfBody + "\n    \"description\": " + task.description + "\n    \"priority\": " + task.priority;
            String body = firstHalfBody + secondHalfBody + "\n    \"completed\": " + task.completed;
            String closeKey = "  }";
            String text = comma + "\n" + startKey + "\n" + body + "\n" + closeKey;
            String startText = startKey + "\n" + body + "\n" + closeKey;


            if (lastKey != 0) {
                peopleData.add(lastKey, text);
                peopleData.add(lastKey + 1, "   {");

                Files.writeString(peopleDataFile, "");
                for (String line : peopleData) {
                    Files.writeString(peopleDataFile, line);
                }

            } else if (lastKey == 0) { // If there wasn't anything before
                int start = 0;

                for (int a =  0; a < peopleData.size(); a++) {
                    String line = peopleData.get(a);

                    if (line.equals("[")) { //getting the index
                        start = a + 1;
                        break;
                    }
                }

                peopleData.add(start, startText);

            }

        } catch (IOException e) {
            System.out.println("Sorry, an error occurred. Error: " + e);
        }
    }
}
