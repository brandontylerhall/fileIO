import util.Input;

import java.io.IOException;

public class ContactsApp {
    public static void main(String[] args) throws IOException {
        try {
            ContactManager contactManager = new ContactManager();
            Input in = new Input();

            while (!contactManager.quit()) {
                System.out.println("Select the function number you would like to perform.");
                System.out.printf("" +
                        "1. Add a contact%n" +
                        "2. Remove a contact%n" +
                        "3. Show contact list%n" +
                        "4. Search contact list by name%n" +
                        "5. Close program%n" +
                        "> ");
                int userSelection = in.getInt();
                switch (userSelection) {
                    case 1 -> contactManager.addContact();
                    case 2 -> contactManager.removeContact();
                    case 3 -> contactManager.showContacts();
                    case 4 -> contactManager.searchByName();
                    case 5 -> contactManager.quitFlag();
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while adding contact: " + e.getMessage());
        }
    }
}