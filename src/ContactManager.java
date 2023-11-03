import util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactManager {
    FileManager fileManager = new FileManager();
    public Path directory = fileManager.getDataDirectory();
    public Path file = fileManager.getDataFile();
    Input in = new Input();
    boolean quit = false;

    public void showContacts() throws IOException {
        List<String> contactList = Files.readAllLines(file);
        for (String contact : contactList) {
            System.out.printf("Name | Phone Number %n" +
                    "-------------------%n" +
                    "| %s |%n", contact);
        }
    }

    public void addContact() throws IOException {

    }

    public void removeContact() throws IOException {

    }

    public void searchByName() {

    }

    public void quitFlag() {
        quit = true;
    }

    public boolean quit() {
        return quit;
    }
}