package util;

import java.util.Scanner;

public class Input {
    private Scanner in;

    public Input() {
        in = new Scanner(System.in);
    }

    public String getLine() { return in.nextLine(); }

    public boolean yes() {
        String userInput = in.nextLine().toLowerCase();
        switch (userInput) {
            case "n", "no", "uh uh", "nope", "narp", "nah", "nop" -> {
                return false;
            }
            case "y", "ye", "yes", "yea", "yeah", "yep", "yarp", "yuh", "yeh" -> {
                return true;
            }
            case "maybe", "mayhaps", "perhaps" -> {
                System.out.println("hmmmm.... so you do? k.");
                return yes();
            }
            default -> {
                return yes();
            }
        }
    }

    public int getInt(int min, int max) {
        System.out.printf("Please enter a number between %d and %d: ", min, max);
        int userInput = in.nextInt();

        if (userInput < min || userInput > max) {
            return getInt(min, max);
        }

        return userInput;
    }

    public int getInt() {
        return in.nextInt();
    }

    public double getMinMax(double min, double max) {
        System.out.printf("Please enter a number between %.2f and %.2f: ", min, max);
        double userInput = in.nextDouble();

        if (userInput < min || userInput > max) {
            return getMinMax(min, max);
        }
        return userInput;
    }

    public double getDouble() {
        return in.nextDouble();
    }
}