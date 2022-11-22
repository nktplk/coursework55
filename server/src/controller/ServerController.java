package controller;

import com.model.user.User;
import connection.ConnectionManager;
import database.handler.DBHandler;

import java.io.IOException;

public class ServerController {
    private ConnectionManager connectionManager;
    private DBHandler dbHandler;

    public ServerController(ConnectionManager connectionManager) {
        this.dbHandler = new DBHandler();
        this.connectionManager = connectionManager;
    }

    public void work() throws IOException {
        String message = null;

        do {
            switch (message = connectionManager.readString()) {
                case "authorize":
                    System.out.println("authorize");
                    connectionManager.sendObject(Operations.GetAuthorization((User)connectionManager.readObject(), this.dbHandler));
                    break;

                case "regist":
                    connectionManager.sendObject(Operations.GetRegistration((User)connectionManager.readObject(), this.dbHandler));
                    break;
            }

        } while (message != null);
    }
}
