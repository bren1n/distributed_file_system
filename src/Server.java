package src;

import java.rmi.*;

public class Server {
    public Server() {
        try {
            Service service = new Task();
            Naming.rebind(Task.getUri(), service);
         } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
