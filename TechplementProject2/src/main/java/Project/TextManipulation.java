package Project;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Subclass for text manipulation operations.
 */
public class TextManipulation {
    private Scanner scanner;

    public TextManipulation(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Method to execute text manipulation operations based on user input.
     */
    public void execute() {
        System.out.print("Enter the text to manipulate: ");
        String text = scanner.nextLine();

        while (true) {
            System.out.println("\nSelect a text operation:");
            System.out.println("1. Convert to uppercase");
            System.out.println("2. Convert to lowercase");
            System.out.println("3. Count words");
            System.out.println("4. Find pattern");
            System.out.println("5. Back to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.println("Uppercase: " + toUppercase(text));
                    break;
                case 2:
                    System.out.println("Lowercase: " + toLowercase(text));
                    break;
                case 3:
                    System.out.println("Word count: " + wordCount(text));
                    break;
                case 4:
                    System.out.print("Enter the pattern to find: ");
                    String pattern = scanner.nextLine();
                    System.out.println("Pattern found: " + findPattern(text, pattern));
                    break;
                case 5:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Converts the given text to uppercase.
     */
    private String toUppercase(String text) {
        return text.toUpperCase();
    }

    /**
     * Converts the given text to lowercase.
     */
    private String toLowercase(String text) {
        return text.toLowerCase();
    }

    /**
     * Counts the number of words in the given text.
     */
    private int wordCount(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    /**
     * Finds if the given pattern exists in the text.
     * Returns true if found, false otherwise.
     */
    private boolean findPattern(String text, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        return m.find();
    }
}
