package com.miguel.springbackend.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
public class JSONCounter{

    public static List<String> counterQuery() throws IOException {

        Path counterList = Path.of("data").resolve(Path.of("counter.txt"));

        List<String> counter = Files.readAllLines(counterList);

        return counter;

    }

    public static String counterQueryOne(int id) throws IOException {

        List<String> counter = counterQuery();

        return counter.get(id);

    }

    public static String counterQueryLast() throws IOException {

        List<String> counter = counterQuery();

        return counter.getLast();
    }

    public static int counterSizeQuery(int x) throws IOException {
        List<String> counter = counterQuery();

        if (x == 1) {
            return counter.size();
        } else {
            return counter.size() - 1;
        }
    }

    public static void counterDeleteOne(int id) throws IOException {

        Path counterFile = Path.of("data").resolve(Path.of("counter.txt"));
        List<String> counter = counterQuery();

        for (String number : counter) {
            if (Integer.parseInt(number) == id) {
                counter.remove(number);
                break;
            }
        }

        Files.writeString(counterFile, "");

        for (int i = 0; i < counter.size(); i++) {
            String line = counter.get(i);

            if (i == 0) {
                Files.writeString(counterFile, line, StandardOpenOption.APPEND);
            } else {
                Files.writeString(counterFile, "\n" + line, StandardOpenOption.APPEND);
            }
        }

    }

    public static void counterDeleteAll() throws IOException {
        Path counterFile = Path.of("data").resolve(Path.of("counter.txt"));

        Files.writeString(counterFile, "");
    }

    public static String counterCreate() throws IOException {

        Path counterFile = Path.of("data").resolve(Path.of("counter.txt"));
        List<String> counter = counterQuery();

        String adding = counter.getLast();
        if (adding.trim().isEmpty()) {
            adding = "0";
        } else {
            adding = String.valueOf(Integer.parseInt(adding) + 1);
        }

        counter.add(adding);

        for (int i = 0; i < counter.size(); i++) {

            String line = counter.get(i);

            if (i == 0) {
                Files.writeString(counterFile, line);
            } else {
                Files.writeString(counterFile, line, StandardOpenOption.APPEND);
            }

        }

        return "Created element id: " + adding;
    }

    @GetMapping("/tasks/last-id")
    public int counterQueryLastID() throws IOException{
        List<String> list = counterQuery();

        int lastID = Integer.parseInt(list.getLast());

        return lastID;
    }
}
