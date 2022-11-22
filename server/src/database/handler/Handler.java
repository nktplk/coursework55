package database.handler;

import com.model.user.User;

import java.sql.Connection;

public interface Handler {
    Connection getDBConnection();
    User Authorization(User user);
    Object Registration(User user);

}
