package Project;

import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Subclass for file encryption and decryption operations.
 */
public class FileEncryptionDecryption {
    private Scanner scanner;

    public FileEncryptionDecryption(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Method to execute file encryption or decryption based on user input.
     */
    public void execute() {
        System.out.println("Enter the path of the file to encrypt/decrypt:");
        String filePath = scanner.nextLine();
        System.out.println("Enter the password:");
        String password = scanner.nextLine();

        System.out.println("Do you want to (E)ncrypt or (D)ecrypt?");
        String action = scanner.nextLine().toUpperCase();

        try {
            File inputFile = new File(filePath);
            File outputFile;
            if (action.equals("E")) {
                outputFile = new File(filePath + ".enc");
                encryptFile(password, inputFile, outputFile);
                System.out.println("File encrypted successfully.");
            } else if (action.equals("D")) {
                if (!filePath.endsWith(".enc")) {
                    System.out.println("File extension should be .enc for decryption.");
                    return;
                }
                outputFile = new File(filePath.replace(".enc", ""));
                decryptFile(password, inputFile, outputFile);
                System.out.println("File decrypted successfully.");
            } else {
                System.out.println("Invalid action. Please enter E or D.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Encrypts the given inputFile using AES encryption with the provided password.
     * Writes the encrypted content to the outputFile.
     */
    private void encryptFile(String password, File inputFile, File outputFile) throws Exception {
        SecretKey secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());
        byte[] outputBytes = cipher.doFinal(inputBytes);

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(outputBytes);
        }
    }

    /**
     * Decrypts the given inputFile (assumed to be encrypted) using AES decryption with the provided password.
     * Writes the decrypted content to the outputFile.
     */
    private void decryptFile(String password, File inputFile, File outputFile) throws Exception {
        SecretKey secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());
        byte[] outputBytes = cipher.doFinal(inputBytes);

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(outputBytes);
        }
    }

    /**
     * Generates a SecretKey using the SHA-1 hash of the provided password,
     * ensuring it's compatible with AES encryption.
     */
    private SecretKey generateKey(String password) throws Exception {
        byte[] key = password.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only first 128 bits for AES
        return new SecretKeySpec(key, "AES");
    }
}
