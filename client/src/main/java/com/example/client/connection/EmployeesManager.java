package com.example.client.connection;

import com.model.employee.Employee;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class EmployeesManager extends ConnectionManager {
    public EmployeesManager (Socket clientSocket) {
        super(clientSocket);
    }
    public LinkedList<Employee> getAllEmployees() {
        sendString("allEmployeeTable");
        try {
            return (LinkedList<Employee>)readMessage.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
