import java.rmi.RemoteException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is an implementation of the ICommon interface methods for file operations.
 */
public class Common implements ICommon {

    // Get the current directory of the server
    static String currentDir = Paths.get("").toAbsolutePath().toString();

    /**
     * Method to upload a file to the server.
     * 
     * @param fileName The name of the file to upload.
     * @param data The byte array containing the file data.
     * @return boolean indicating success or failure of the upload operation.
     * @throws RemoteException if a remote communication error occurs.
     */
    @Override
    public boolean upload(String fileName, byte[] data) throws RemoteException {
        Path path = Paths.get(currentDir + "\\Server\\" + fileName);
        try {
            Files.write(path, data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to download a file from the server.
     * 
     * @param fileName The name of the file to download.
     * @return byte array containing the file data, or null if an error occurs.
     * @throws RemoteException if a remote communication error occurs.
     */
    @Override
    public byte[] download(String fileName) throws RemoteException {
        Path path = Paths.get(currentDir + "\\Server\\" + fileName);
        byte[] data = null;
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Method to delete a file from the server.
     * 
     * @param fileName The name of the file to delete.
     * @return boolean indicating success or failure of the delete operation.
     * @throws RemoteException if a remote communication error occurs.
     */
    @Override
    public boolean delete(String fileName) throws RemoteException {
        try {
            Files.deleteIfExists(Paths.get(currentDir + "\\Server\\" + fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to rename a file on the server.
     * 
     * @param oldFileName The current name of the file.
     * @param newFileName The new name of the file.
     * @return boolean indicating success or failure of the rename operation.
     * @throws RemoteException if a remote communication error occurs.
     */
    @Override
    public boolean rename(String oldFileName, String newFileName) throws RemoteException {
        Path path = Paths.get(currentDir + "\\Server\\" + oldFileName);
        try {
            Files.move(path, path.resolveSibling(newFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
