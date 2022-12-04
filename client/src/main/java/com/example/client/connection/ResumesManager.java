package com.example.client.connection;

import com.model.resume.Resume;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class ResumesManager extends ConnectionManager {
    public ResumesManager(Socket clientSocket) {
        super(clientSocket);
    }
    public LinkedList<Resume> getAllResumes() {
        sendString("AllResumeTable");
        try {
            return (LinkedList<Resume>)readMessage.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}