package com.example.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileTest {

    private static final Logger logger = LogManager.getLogger(FileTest.class);

    private static final String NEW_LINE = System.lineSeparator();

    // create subdirectory path


    @BeforeAll
    static void setup() {
        Path myDir = Paths.get("C:\\workspace\\Java25\\target\\files");
        // if the subdirectory doesn't exist then create it
        if (Files.notExists(myDir)) {
            try { Files.createDirectory(myDir); }
            catch (Exception e ) { e.printStackTrace(); }
        }

        Path myFile = Paths.get("C:\\workspace\\Java25\\target\\files\\myfile.txt");
        // if the file doesn't exist then create it
        if (Files.notExists(myFile)) {
            try { Files.createFile(myFile); }
            catch (Exception e ) { e.printStackTrace(); }
        }
    }

    @AfterAll
    static void done() {
        Path myFile = Paths.get("C:\\workspace\\Java25\\target\\files\\myfile.txt");
        // if the file doesn't exist then create it
        if (Files.notExists(myFile)) {
            try { Files.deleteIfExists(myFile); }
            catch (Exception e ) { e.printStackTrace(); }
        }

        Path fileDir = Paths.get("C:\\workspace\\Java25\\target\\files");
        // if the subdirectory exists then delete it
        if (Files.notExists(fileDir)) {
            try { Files.deleteIfExists(fileDir); }
            catch (Exception e ) { e.printStackTrace(); }
        }
    }

    @Test
    void assertMyDirExists() {
        assertTrue(Files.exists(Paths.get("C:\\workspace\\Java25\\target\\files")));
        assertTrue(Files.exists(Paths.get(".\\target\\files")));
    }

    @Test
    void assertMyFileExists() {
        assertTrue(Files.exists(Paths.get("C:\\workspace\\Java25\\target\\files\\myfile.txt")));
        assertTrue(Files.exists(Paths.get(".\\target\\files\\myfile.txt")));
    }

    @Test
    void assertPaths() throws IOException {
        Path myDir = Paths.get("C:\\workspace\\Java25\\target\\files");
        assertEquals("C:\\workspace\\Java25\\target", myDir.getParent().toString());
        assertEquals("C:\\workspace\\Java25\\target\\files", myDir.toRealPath().toString());
        assertEquals("C:\\workspace\\Java25\\target\\files", myDir.toAbsolutePath().toString());

        Path myFile = Paths.get(".\\target\\files\\myfile.txt");
        assertEquals("myfile.txt", myFile.getFileName().toString());
    }


    @Test
    void writeToFile() {
        Path path = Paths.get("C:\\workspace\\Java25\\target\\files\\myfile.txt");
        //Stream.iterate(initial value, next value)
        Stream.iterate(1, n -> n < 200 , n -> n + 1).limit(100)
                .forEach(x -> {
                    try {
                        appendToFile(path, "Line " + x + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    // append a string to the end of the file
    private static void appendToFile(Path path, String content)
            throws IOException {
        Files.write(path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

    }

    @Test
    void readFromFile() throws IOException {
        Path path = Paths.get("C:\\workspace\\Java25\\target\\files\\myfile.txt");

        List<String> list;
        try (Stream<String> stream = Files.lines(path)) {
            list = stream.collect(Collectors.toList());
        }
        list.forEach(System.out::println);
    }
}
