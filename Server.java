/*
 * @auther - Maharsh Patel, 100209501
 * Project 1 - Part 1
 * this is a server application using java RMI RPC
 * before running the server app please run "start rmiregistry 4321" on command prompt
 */
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public Server(){}

    public static void main(String[] args) {
        System.out.println("Server started");
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        try{
            // Object which is going to provide service
            Common op = new Common();
            // Export remote object to Java RMI runtime so that it can receive incoming calls
            ICommon obj = (ICommon)UnicastRemoteObject.exportObject(op, 0);
            // create a java RMI registry and bind the remote object with a name which client can refer 
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",4321);
            reg.rebind("op", obj);
            System.err.println("Server ready");
        }catch(Exception e){
            System.err.println("Exception on server: " + e.toString());
            e.printStackTrace();
        }
    }
}
