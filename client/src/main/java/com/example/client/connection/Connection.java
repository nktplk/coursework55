package com.example.client.connection;

import com.model.user.UserTable;

import java.io.IOException;
import java.net.Socket;

public class Connection {
    public static ConnectionManager connectionManager;
    //public static CountryManager countryManager;
    //public static CategoryManager categoryManager;
    //public static MessageManager messageManager;
    public static EmployeesManager employeesManager;
    public static DepartmentsManager departmentsManager;
    public static ResumesManager resumesManager;
    public static SchedulesManager SchedulesManager;
    public static UsersManager usersManager;

    public void connectToServer(String host, int port) {
        try {
            Socket clientSocket = new Socket(host, port);
            //Socket countrySocket = new Socket(host, port);
            //Socket categorySocket = new Socket(host, port);
            Socket employeesSocket = new Socket(host, port);
            Socket usersSocket = new Socket(host, port);
            Socket resumesSocket = new Socket(host, port);
            Socket departmentsSocket = new Socket(host, port);
            Socket SchedulesSocket = new Socket(host, port);
            connectionManager = new ConnectionManager(clientSocket);
            departmentsManager = new DepartmentsManager(departmentsSocket);
            //countryManager = new CountryManager(countrySocket);
            //categoryManager = new CategoryManager(categorySocket);
            SchedulesManager = new SchedulesManager(SchedulesSocket);
            employeesManager = new EmployeesManager(employeesSocket);
            resumesManager = new ResumesManager(resumesSocket);
            usersManager = new UsersManager(usersSocket);
            //messageManager = new MessageManager(messageSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startEnd() {
        System.out.println("hello");
    }
}
