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
    public Path file = fileManager.getDataFile();
    Input in = new Input();
    boolean quit = false;

    public void showContacts() throws IOException {
        List<String> contactList = Files.readAllLines(file);

        // Format and print the contact list with equal width columns
        String format = "| %-13s | %-12s |%n";
        System.out.printf(format, "Name", "Phone Number");
        System.out.println("--------------------------------");
        for (String contact : contactList) {
            String[] parts = contact.split(" \\| ");
            if (parts.length == 2) {
                System.out.printf(format, parts[0], parts[1]);
            }
        }
    }

    public void addContact() throws IOException {
        try {
            System.out.print("Enter the contact's name: ");
            String newContactName = in.getLine();

            System.out.print("Enter the contact's phone number: ");
            String newContactNumber = in.getLine();

            String formattedNumber = newContactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");

            Files.write(
                    file,
                    Arrays.asList(newContactName + " | " + formattedNumber),
                    StandardOpenOption.APPEND
            );

            System.out.println("Contact added successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while adding contact: " + e.getMessage());
        }
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