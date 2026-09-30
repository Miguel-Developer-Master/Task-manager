package com.miguel.springbackend.apis;

import java.io.IOException;
import java.nio.file.*;

public class JSONCreator {

    public static void create() throws IOException {
        Path folder = Path.of("data");
        Path counter = folder.resolve("counter.txt");
        Path json = folder.resolve("people.json");

        if (!Files.exists(folder)) {
            Files.createDirectory(folder);
        }

        if (!Files.exists(counter)) {
            Files.createFile(counter);
        }

        if (!Files.exists(json)) {
            Files.createFile(json);
            Files.writeString(json,"[]");
        }
    }

}
