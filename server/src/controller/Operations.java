package controller;

import com.model.user.User;
import database.handler.Handler;

public class Operations {
    public static User GetAuthorization(User user, Handler handler) { return handler.Authorization(user); }
    public static Object GetRegistration(User user, Handler handler) { return handler.Registration(user); }
}
