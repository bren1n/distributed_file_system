package src;

import java.net.MalformedURLException;
import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        try {
            Service service = (Service) Naming.lookup(Task.getUri());
            runCommand(service, args);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    private static void runCommand(Service service, String[] args) {
        String command = args[0];

        try {
            switch (command) {
                case "list":
                    String[] fileList = service.listFiles(args[1]);
                    System.out.println("List of files and directories:");
                    for (int i = 0; i < fileList.length; i++) {
                        System.out.println(fileList[i]);
                    }
                    break;
                case "createfile":
                    boolean isFileCreated = service.createFile(args[1]);
                    if (isFileCreated) {
                        System.out.println("Successful file creation.");
                    } else {
                        System.out.println("File creation failed.");
                    }
                    break;
                case "createdir":
                    boolean isDirCreated = service.createDirectory(args[1]);
                    if (isDirCreated) {
                        System.out.println("Successful directory creation.");
                    } else {
                        System.out.println("Directory creation failed.");
                    }
                    break;
                default:
                    System.out.println("Enter a valid command.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
