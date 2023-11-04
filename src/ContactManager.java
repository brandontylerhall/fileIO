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
        System.out.println("|---------------|--------------|");
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
            /*formatting to make numbers (xxx) xxx-xxxx*/
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
        try {
            List<String> contactList = Files.readAllLines(file);

            /*formatting code*/
            String format = "| %-13s | %-12s |%n";
            System.out.printf(format, "Name", "Phone Number");
            System.out.println("|---------------|--------------|");

            /*removes the delimiter of | in the .txt and formats the names/numbers*/
            for (String contact : contactList) {
                String[] parts = contact.split("\\s*\\|\\s*");
                String name = parts[0].trim();
                String phoneNumber = parts[1].trim();
                System.out.printf(format, name, phoneNumber);
            }

            System.out.print("Enter the contact's name to remove: ");
            String contactToKill = in.getLine();

            /*creates new, empty list of contacts and adds back every contact minus the one you removed*/
            List<String> updatedContactList = new ArrayList<>();
            for (String contact : contactList) {
                String[] parts = contact.split("\\s*\\|\\s*");
                String name = parts[0].trim();
                if (name.equalsIgnoreCase(contactToKill)) {
                    System.out.println("Contact removed successfully!");
                    continue;
                }
                updatedContactList.add(contact);
            }

            Files.write(file, updatedContactList);
        } catch (IOException e) {
            System.out.println("Error occurred while removing contact: " + e.getMessage());
        }
    }

    public void searchByName() throws IOException {
        List<String> contactList = Files.readAllLines(file);
        List<String> listOfSearchedContacts = new ArrayList<>();

        System.out.print("What is the name of the contact you wish to search: ");
        String contactToFind = in.getLine().trim();

        /*splits name into first and last name pieces*/
        for (String contact : contactList) {
            String[] parts = contact.split("\\s*\\|\\s*");
            String name = parts[0].trim();
            String[] nameParts = name.split("\\s+");

            // Compare search input with both first and last name parts
            for (String namePart : nameParts) {
                if (namePart.equalsIgnoreCase(contactToFind)) {
                    listOfSearchedContacts.add(contact);
                    break;
                }
            }
        }

        /*print the search results*/
        if (listOfSearchedContacts.isEmpty()) {
            System.out.println("There's nobody around here that has that name, pardner.");
        } else {
            System.out.println("Search results:");
            String format = "| %-13s | %-12s |%n";
            System.out.printf(format, "Name", "Phone Number");
            System.out.println("|---------------|--------------|");
            for (String result : listOfSearchedContacts) {
                String[] parts = result.split("\\s*\\|\\s*");
                String name = parts[0].trim();
                String phoneNumber = parts[1].trim();
                System.out.printf(format, name, phoneNumber);
            }
        }
    }

    public void quitFlag() {
        quit = true;
    }

    public boolean quit() {
        return quit;
    }
}