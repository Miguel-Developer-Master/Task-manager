package com.miguel.springbackend.apis;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class JSONCreator {

    public static void createJSON() throws IOException {
        Path folder = Path.of("data");
        Path people = folder.resolve("people.json");
        Path counter = folder.resolve("counter.txt");

        if (!Files.exists(folder)) {
            Files.createDirectory(folder);

            if (!Files.exists(people)) {
                Files.createFile(people);
            }

            if (!Files.exists(counter)) {
                Files.createFile(counter);
            }
        }

        List<String> peopleData = Files.readAllLines(people);

        if (peopleData.size() < 2) {
            Files.writeString(
                    people,
                    "[\n" + "\n]"
            );
        }

    }

}
