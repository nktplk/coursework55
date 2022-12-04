package com.example.client.connection;

import com.model.schedule.Schedule;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class SchedulesManager extends ConnectionManager {
    public SchedulesManager(Socket clientSocket) {
        super(clientSocket);
    }
    public LinkedList<Schedule> getAllSchedules() {
        sendString("AllScheduleTable");
        try {
            return (LinkedList<Schedule>)readMessage.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}