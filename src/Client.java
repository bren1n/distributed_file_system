package src;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Service service = (Service) Naming.lookup("rmi://127.0.0.1:1098/FileService");
            System.out.println("Client connected.");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("\u001B[32m" + "$ ");
                String line = sc.nextLine();
                System.out.print("\u001B[0m");
                runCommand(service, line.split(" "));
            }
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
                case "ls":
                    String[] fileList = service.listFiles(args[1]);
                    System.out.println("List of files and directories:");
                    for (int i = 0; i < fileList.length; i++) {
                        System.out.println(fileList[i]);
                    }
                    break;
                case "mkfile":
                    boolean isFileCreated = service.createFile(args[1]);
                    if (isFileCreated) {
                        System.out.println("Successful file creation.");
                    } else {
                        System.out.println("File creation failed.");
                    }
                    break;
                case "mkdir":
                    boolean isDirCreated = service.createDirectory(args[1]);
                    if (isDirCreated) {
                        System.out.println("Successful directory creation.");
                    } else {
                        System.out.println("Directory creation failed.");
                    }
                    break;
                case "rmfile":
                    boolean isFileDeleted = service.deleteFile(args[1]);
                    if (isFileDeleted) {
                        System.out.println("File deleted with success.");
                    } else {
                        System.out.println("Error deleting file.");
                    }
                    break;
                case "rmdir":
                    service.deleteDirectory(args[1]);
                    System.out.println("Directory deleted with success.");
                    break;
                case "cp":
                    service.copyFile(args[1], args[2]);
                    System.out.println("File copied with success.");
                    break;
                case "open":
                    String content = service.openFile(args[1]);
                    System.out.println("Content from " + args[1] + ":");
                    System.out.print(content);
                    break;
                case "write":
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Type file content:");
                    String contentToWrite = sc.nextLine();
                    boolean isWritten = service.writeFile(args[1], contentToWrite);
                    if (isWritten) {
                        System.out.println("Successfully written file");
                    } else {
                        System.out.println("File can't be written.");
                    }
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Enter a valid command.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
