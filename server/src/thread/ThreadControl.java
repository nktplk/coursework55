package thread;

import connection.ConnectionManager;
import controller.ServerController;

import java.io.IOException;
import java.net.Socket;

public class ThreadControl extends Thread {
    private Socket clientSocket;

    public ThreadControl(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        var manager = new ConnectionManager(clientSocket);
        ServerController serverController = new ServerController(manager);

        try {
            serverController.work();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
