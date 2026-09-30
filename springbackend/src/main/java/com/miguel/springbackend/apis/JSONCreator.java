package com.miguel.springbackend.apis;

import java.io.IOException;
import java.nio.file.*;

public class COUNTERCreator {

    public static void createCOUNTER() throws IOException {
        Path folder = Path.of("data");
        Path counter = folder.resolve("counter.txt");

        if (!Files.exists(folder)) {
            Files.createDirectory(folder);
        }

        if (!Files.exists(counter)) {
            Files.createFile(counter);
        }
    }

}
