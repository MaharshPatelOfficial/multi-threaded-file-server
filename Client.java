import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * This is a client application using Java RMI (Remote Method Invocation) RPC (Remote Procedure Call).
 * 
 * To run this application:
 * 1. Start the RMI registry with the command "start rmiregistry 4321" on the command prompt.
 * 2. Run the server application.
 * 3. Run this client application.
 */
public class Client {
    public static void main(String[] args) throws IOException, NotBoundException {
        // Get the current directory of the client
        String currentDir = Paths.get("").toAbsolutePath().toString();

        // Connect to the RMI registry on the server's host at port 4321
        Registry reg = LocateRegistry.getRegistry("127.0.0.1", 4321);

        // Look for the remote object by name "op"
        ICommon obj = (ICommon) reg.lookup("op");

        // Initialize variables for user input and menu choice
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do {
            // Display menu options
            System.out.println("1. Upload");
            System.out.println("2. Download");
            System.out.println("3. Delete");
            System.out.println("4. Rename");
            System.out.println("Anything else to Exit");

            // Read user's choice
            choice = sc.nextInt();
            sc.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    // Upload a file
                    System.out.println("Enter file name to be uploaded:");
                    String fileName = sc.nextLine();
                    Path path = Paths.get(currentDir + "\\Client\\" + fileName);
                    byte[] data = Files.readAllBytes(path);

                    if (obj.upload(fileName, data)) {
                        System.out.println("File uploaded successfully.");
                    } else {
                        System.out.println("Error uploading file.");
                    }
                    break;

                case 2:
                    // Download a file
                    System.out.println("Enter file name to be downloaded:");
                    String fileName1 = sc.nextLine();
                    Path path1 = Paths.get(currentDir + "\\Client\\" + fileName1);
                    byte[] data1 = obj.download(fileName1);

                    if (data1 != null) {
                        Files.write(path1, data1);
                        System.out.println("Downloaded successfully.");
                    } else {
                        System.out.println("Error downloading file.");
                    }
                    break;

                case 3:
                    // Delete a file
                    System.out.println("Enter file name to be deleted:");
                    String fileName2 = sc.nextLine();

                    if (obj.delete(fileName2)) {
                        System.out.println("File deleted successfully.");
                    } else {
                        System.out.println("Error deleting file.");
                    }
                    break;

                case 4:
                    // Rename a file
                    System.out.println("Enter file name to be renamed:");
                    String oldFileName = sc.nextLine();
                    System.out.println("Enter new file name:");
                    String newFileName = sc.nextLine();

                    if (obj.rename(oldFileName, newFileName)) {
                        System.out.println("File renamed successfully.");
                    } else {
                        System.out.println("Error renaming file.");
                    }
                    break;

                default:
                    // Exit the application
                    choice = 0;
                    break;
            }
        } while (choice != 0);

        // Close the scanner
        sc.close();
    }
}
