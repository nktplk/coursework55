package database.handler;

import com.model.user.CurrentUser;
import com.model.user.User;
import com.hashing.JBCrypt;
import database.literals.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static database.literals.Сonstants.*;

public class DBHandler extends Configuration implements Handler{
    private Connection dbConnection;

    public DBHandler() {
        getDBConnection();
    }

    @Override
    public Connection getDBConnection() {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            return dbConnection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User Authorization(User user) {
        var listOfUsers = new ArrayList<User>();
        var getAuthorization = "SELECT "
                + USERS_ID + ", " + USERS_LOGIN + ", "
                + USERS_PASSWORD + ", " + USERS_ROLE
                + " FROM " + USER_TABLE + " WHERE "
                + USERS_LOGIN + " = ?";
        try {
            var request = dbConnection.prepareStatement(getAuthorization);
            request.setString(1, user.getLogin());
            var table = request.executeQuery();
            while (table.next()) {
                CurrentUser candidateOnInter;
                if (!JBCrypt.checkpw(user.getPassword(), table.getString(3))) {
                    System.out.println(table.getString(3));
                    System.out.println(user.getPassword());
                    System.out.println("null");
                    return null;
                }
                user.setRole(table.getString(4));
                System.out.println(user.getLogin());
                return user;
            }
            return null;

            /*prSt.setString(2, user.getPassword());
            var resSet = prSt.executeQuery();
            while (resSet.next()) {
                System.out.println("good");
                return user;
            }*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("bad");
        //return null;
    }

    public Object Registration(User user) {
        var countOfUsers = 0;
        var listOfUsers = new ArrayList<User>();
        var getUsers = "SELECT " + USERS_LOGIN + " FROM " + USER_TABLE
                + " WHERE " + USERS_LOGIN + " = ?";
        try {
            var request = dbConnection.prepareStatement(getUsers);
            request.setString(1, user.getLogin());
            var table = request.executeQuery();
            while (table.next()) {
                countOfUsers++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (countOfUsers != 0) {
            return null;
        }
        var getRegistration = "INSERT INTO " + USER_TABLE + " (" + USERS_LOGIN + ", "
                + USERS_PASSWORD + ", " + USERS_ROLE + ") VALUES (?, ?, ?)";
        try {
            var i = 0;
            var request = dbConnection.prepareStatement(getRegistration);
            request.setString(1, user.getLogin());
            request.setString(2, JBCrypt.hashpw(user.getPassword(), JBCrypt.gensalt()));
            request.setString(3, user.getRole());
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
