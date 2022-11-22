package start;

import thread.ThreadControl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        int port;
        var scanner = new Scanner(System.in);

        System.out.print("Введите порт для сервера: ");
        port = scanner.nextInt();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Сервер работает\nОжидание подключения");

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ThreadControl myThread = null;
            try {
                myThread = new ThreadControl(clientSocket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            myThread.start();
            System.out.println("Новое подключение. IP - " + clientSocket.getInetAddress().toString().replace("/", "") + " Порт - " + clientSocket.getPort());
        }
    }
}
