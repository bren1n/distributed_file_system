package src;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        System.setProperty("java.rmi.server.hostname","127.0.0.1");
        Service service = new Task();
        LocateRegistry.createRegistry(1098);
        Naming.rebind("rmi://127.0.0.1:1098/FileService", service);
        System.out.println("Server started.");
    }
}
