package com.example.client.connection;

import java.io.IOException;
import java.net.Socket;

public class Connection {
    public static ConnectionManager connectionManager;
    //public static CountryManager countryManager;
    //public static CategoryManager categoryManager;
    //public static MessageManager messageManager;
    //public static UsersManager usersManager;

    public void connectToServer(String host, int port) {
        try {
            Socket clientSocket = new Socket(host, port);
            //Socket countrySocket = new Socket(host, port);
            //Socket categorySocket = new Socket(host, port);
            //Socket usersSocket = new Socket(host, port);
            //Socket messageSocket = new Socket(host, port);
            connectionManager = new ConnectionManager(clientSocket);
            //countryManager = new CountryManager(countrySocket);
            //categoryManager = new CategoryManager(categorySocket);
            //usersManager = new UsersManager(usersSocket);
            //messageManager = new MessageManager(messageSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startEnd() {
        System.out.println("hello");
    }
}
