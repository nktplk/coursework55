package com.example.client.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ConnectionManager {
    protected ObjectOutputStream sendMessage;
    protected ObjectInputStream readMessage;


    public ConnectionManager(Socket clientSocket) {
        try {
            sendMessage = new ObjectOutputStream(clientSocket.getOutputStream());
            readMessage = new ObjectInputStream(clientSocket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        try {
            sendString("close");
            sendMessage.close();
            readMessage.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(String code, Object object) {
        try {
            sendString(code);
            sendMessage.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendString(String text) {
        try {
            sendMessage.writeObject(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readObject() {
        try {
            return readMessage.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String readString() {
        try {
            return (String)readMessage.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Double readValue() {
        try {
            return (Double)readMessage.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public LinkedList<String> getStrings(String code) {
        sendString(code);
        try {
            return (LinkedList<String>) readMessage.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Integer> getYears() {
        sendString("years");
        try {
            return (LinkedList<Integer>) readMessage.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
