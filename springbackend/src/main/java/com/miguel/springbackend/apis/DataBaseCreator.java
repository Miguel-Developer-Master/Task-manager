package com.miguel.springbackend.apis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreator {

    public static void main(String[] args) throws IOException, SQLException {

        Path path = Path.of("data");

        Path createSQL = path.resolve("create.sql");

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        if (!Files.exists(createSQL)) {
            Files.writeString(
                    createSQL,
                    "CREATE TABLE IF NOT EXISTS TASKS (\n" +
                            "    ID          INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
                            "    TITLE       VARCHAR (30) NOT NULL,\n" +
                            "    DESCRIPTION VARCHAR (75),\n" +
                            "    PRIORITY    TEXT         NOT NULL,\n" +
                            "    COMPLETED   BOOLEAN      DEFAULT (0) \n" +
                            ");"
            );

            String sql = Files.readString(createSQL);

            Connection connection = DriverManager.getConnection("jdbc:sqlite:data/tasks.db");
            Statement statement = connection.createStatement();
            statement.execute(sql);

            statement.close();
            connection.close();
        }
    }
}
