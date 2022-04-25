package src;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Task extends UnicastRemoteObject implements Service {
    private static String SERVER = "localhost";
    private static Integer PORT = 1099;
    private static String SERVICE = "FileService";

    public Task() throws RemoteException {
        super();
    }

    @Override
    public boolean createDirectory(String path) throws RemoteException, IOException {
        boolean isDirectoryCreated = false;

        try {
            File file = new File(path);
            if (file.mkdirs()) {
                isDirectoryCreated = true;
            } else {
                isDirectoryCreated = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDirectoryCreated;
    }

    @Override
    public boolean createFile(String path) throws RemoteException, IOException {
        boolean isFileCreated = false;

        try {
            File file = new File(path);
            if (file.createNewFile()) {
                isFileCreated = true;
            } else {
                isFileCreated = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFileCreated;
    }

    @Override
    public String[] listFiles(String path) throws RemoteException {
        File directory = new File(path);
        String[] dirContent = directory.list();
        return dirContent;
    }

    public static String getUri() throws RemoteException {
        String uri = String.format("rmi://%s:%d/$s", SERVER, PORT, SERVICE);
        return uri;
    }
}
