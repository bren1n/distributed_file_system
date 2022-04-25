package src;

import java.io.IOException;
import java.rmi.*;

public interface Service extends Remote {
    public boolean createDirectory(String path) throws RemoteException, IOException;
//
    public boolean createFile(String path) throws RemoteException, IOException;

    public String[] listFiles(String path) throws RemoteException;

//    public String readFile(String path) throws RemoteException;
//
//    public boolean checkIfFileExists(String path) throws RemoteException;
//
//    public boolean copyFile(String source, String destiny) throws RemoteException;
//
//    public boolean deleteFile(String path) throws RemoteException;
//
//    public boolean deleteDirectory(String path) throws RemoteException;
}
