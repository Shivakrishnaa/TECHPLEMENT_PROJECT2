package Project;
import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Subclass for file compression operation.
 */
public class FileCompression {
    private Scanner scanner;

    public FileCompression(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Method to execute file compression based on user input.
     */
    public void execute() {
        System.out.print("Enter the path of the file to compress: ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Enter the path for the output zip file: ");
        String outputZipFilePath = scanner.nextLine();

        try {
            compressFileToZip(inputFilePath, outputZipFilePath);
            System.out.println("File compressed successfully to " + outputZipFilePath);
        } catch (IOException e) {
            System.err.println("Error compressing file: " + e.getMessage());
        }
    }

    /**
     * Compresses the specified inputFile into a ZIP archive at the specified outputZipFilePath.
     */
    private void compressFileToZip(String inputFilePath, String outputZipFilePath) throws IOException {
        // Create file input stream to read the input file
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             // Create file output stream to write to the ZIP file
             FileOutputStream fos = new FileOutputStream(outputZipFilePath);
             // Wrap the output stream with a ZIP output stream
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            // Create a new ZIP entry for the file
            ZipEntry zipEntry = new ZipEntry(new File(inputFilePath).getName());
            zos.putNextEntry(zipEntry);

            // Buffer for reading the input file
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Read the input file and write to the ZIP file
            while ((bytesRead = fis.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }

            // Close the ZIP entry
            zos.closeEntry();
        }
    }
}
