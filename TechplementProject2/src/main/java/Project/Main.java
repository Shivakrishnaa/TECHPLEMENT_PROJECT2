package Project;

import java.util.Scanner;

/**
 * Main class to handle user interaction and invoke functionalities through subclasses.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Text Manipulation");
            System.out.println("2. File Encryption/Decryption");
            System.out.println("3. File Compression");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    TextManipulation textManipulation = new TextManipulation(scanner);
                    textManipulation.execute();
                    break;
                case 2:
                    FileEncryptionDecryption fileEncryptionDecryption = new FileEncryptionDecryption(scanner);
                    fileEncryptionDecryption.execute();
                    break;
                case 3:
                    FileCompression fileCompression = new FileCompression(scanner);
                    fileCompression.execute();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
