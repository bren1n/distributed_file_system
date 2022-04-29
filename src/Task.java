package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Task extends UnicastRemoteObject implements Service {
    private static String SERVER = "localhost";
    private static Integer PORT = 1099;
    private static String SERVICE = "FileService";
    public String currentDirectory = "./";

    public Task() throws RemoteException {
        super();
    }

    @Override
    public boolean createDirectory(String path) throws RemoteException, IOException {
        boolean isDirectoryCreated = false;

        try {
            File file = new File(this.currentDirectory + path);
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

    @Override
    public boolean deleteFile(String path) throws RemoteException {
        boolean isFileDeleted = false;

        try {
            File file = new File(path);

            if (file.delete()) {
                isFileDeleted = true;
            } else {
                isFileDeleted = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFileDeleted;
    }

    @Override
    public void deleteDirectory(String path) throws RemoteException, IOException {
        try {
            File directory = new File(path);
            File[] files = directory.listFiles();

            for (File file: files) {
                file.delete();
            }

            directory.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copyFile(String source, String destination) throws RemoteException, IOException {
        try {
            Path srcPath = Paths.get(source);
            Path destPath = Paths.get(destination);
            Files.copy(srcPath, destPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String openFile(String path) throws RemoteException, IOException {
        String fileContent = "";

        try {
            fileContent = Files.readString(Paths.get(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileContent;
    }

    @Override
    public void navigateToDirectory(String path) throws IOException {
        this.currentDirectory = path + "/";
    }

    @Override
    public String getCurrentDirectory() {
        return this.currentDirectory;
    }

    public static String getUri() throws RemoteException {
        String uri = String.format("rmi://%s:%d/$s", SERVER, PORT, SERVICE);
        return uri;
    }
}
