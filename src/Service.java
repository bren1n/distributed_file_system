package src;

import java.io.IOException;
import java.rmi.*;

public interface Service extends Remote {
    public boolean createDirectory(String path) throws RemoteException, IOException;

    public boolean createFile(String path) throws RemoteException, IOException;

    public String[] listFiles(String path) throws RemoteException;

    public boolean deleteFile(String path) throws RemoteException;

    public void deleteDirectory(String path) throws RemoteException, IOException;

    public void copyFile(String source, String destiny) throws RemoteException, IOException;

    public String openFile(String path) throws RemoteException, IOException;

    public void navigateToDirectory(String path) throws RemoteException, IOException;

    public String getCurrentDirectory() throws RemoteException;
}
