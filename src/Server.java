package src;

import java.net.MalformedURLException;
import java.rmi.*;

public class Server {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        Service service = new Task();
        Naming.rebind("rmi://localhost:1099/FileService", service);
        System.out.println("Server started.");
    }
}
