/*
 * 
 * 
 * This inteface is common between client and server application
 */
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ICommon extends Remote {
    // method to upload file
    boolean upload(String fileName,byte[] data) throws RemoteException;
    // method to download file
    byte[] download(String fileName) throws RemoteException;
    // method to delete file
    boolean delete(String fileName) throws RemoteException;
    // method to rename file
    boolean rename(String oldFileName, String newFileName) throws RemoteException; 
}
