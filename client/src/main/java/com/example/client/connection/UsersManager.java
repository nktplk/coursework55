package com.example.client.connection;

import com.model.resume.Resume;
import com.model.user.UserTable;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class UsersManager extends ConnectionManager {
    public UsersManager(Socket clientSocket) {
        super(clientSocket);
    }
    public LinkedList<UserTable> getAllUsers() {
        sendString("AllUserTable");
        try {
            return (LinkedList<UserTable>)readMessage.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}