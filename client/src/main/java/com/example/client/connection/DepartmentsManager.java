package com.example.client.connection;

import com.model.department.Department;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class DepartmentsManager extends ConnectionManager {
    public DepartmentsManager(Socket clientSocket) {
        super(clientSocket);
    }
    public LinkedList<Department> getAllDepartments() {
        sendString("AllDepartmentTable");
        try {
            return (LinkedList<Department>)readMessage.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}