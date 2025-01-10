import java.io.*;
import java.nio.file.*;
import java.util.*;

public class InteractiveFileEditor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the File Operations Program!");

        System.out.print("Enter the file name (e.g., example.txt): ");
        String filePath = scanner.nextLine();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter the content to write (type 'END' on a new line to finish):");
                    List<String> linesToWrite = new ArrayList<>();
                    while (true) {
                        String line = scanner.nextLine();
                        if (line.equalsIgnoreCase("END")) break;
                        linesToWrite.add(line);
                    }
                    writeToFile(filePath, linesToWrite);
                    break;

                case 2:
                    List<String> lines = readFromFile(filePath);
                    System.out.println("File Contents:");
                    lines.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter the text to find: ");
                    String target = scanner.nextLine();
                    System.out.print("Enter the replacement text: ");
                    String replacement = scanner.nextLine();
                    modifyFile(filePath, target, replacement);
                    break;

                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void writeToFile(String filePath, List<String> lines) {
        try {
            Files.write(Paths.get(filePath), lines);
            System.out.println("Content successfully written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<String> readFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void modifyFile(String filePath, String target, String replacement) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            List<String> modifiedLines = new ArrayList<>();
            for (String line : lines) {
                modifiedLines.add(line.replace(target, replacement));
            }
            Files.write(path, modifiedLines);
            System.out.println("File successfully modified.");
        } catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }
}