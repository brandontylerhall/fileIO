package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private Path dataDirectory;
    private Path dataFile;

    public FileManager() {
        String directory = "src/data";
        dataDirectory = Paths.get(directory);
        String filename = "contacts.txt";
        dataFile = dataDirectory.resolve(filename);

        if (Files.notExists(dataDirectory)) {
            try {
                Files.createDirectories(dataDirectory);
            } catch (IOException e) {
                throw new RuntimeException("Error creating data directory: " + e.getMessage());
            }
        }

        if (!Files.exists(dataFile)) {
            try {
                Files.createFile(dataFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating data file: " + e.getMessage());
            }
        }
    }

    public Path getDataDirectory() {
        return dataDirectory;
    }

    public Path getDataFile() {
        return dataFile;
    }
}