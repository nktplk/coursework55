package com.example.client;


import animatefx.animation.FadeIn;
import com.example.client.connection.Connection;
import com.example.client.controllers.windows.AdminWindowController;
import com.example.client.controllers.windows.AuthorizationWindowController;
import com.example.client.controllers.windows.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("authorizationWindow.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
//        stage.setTitle("My App");
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader authorizationWindowPage = new FXMLLoader();
        FXMLLoader mainWindowPage = new FXMLLoader();
        FXMLLoader adminWindowPage = new FXMLLoader();

        authorizationWindowPage.setLocation(getClass().getResource("/com/example/client/windows/authorizationWindow.fxml"));
        mainWindowPage.setLocation(getClass().getResource("/com/example/client/windows/mainWindow.fxml"));
        adminWindowPage.setLocation(getClass().getResource("/com/example/client/windows/adminWindow.fxml"));

        Scene authorizationWindowScene = new Scene(authorizationWindowPage.load(), 1200, 800);
        //Parent mainWindowPane = mainWindowPage.load();
        //Parent adminWindowPane = adminWindowPage.load();
        Scene mainWindowScene = new Scene(mainWindowPage.load(), 1200, 800);
        Scene adminWindowScene = new Scene(adminWindowPage.load(), 1200, 800);

        AuthorizationWindowController mainWindowFromAuthorizationWindowPaneController = (AuthorizationWindowController) authorizationWindowPage.getController();
        mainWindowFromAuthorizationWindowPaneController.setMainWindowScene(mainWindowScene);
        mainWindowFromAuthorizationWindowPaneController.setAdminWindowScene(adminWindowScene);

        MainWindowController authorizationWindowFromMainWindowPaneController = (MainWindowController) mainWindowPage.getController();
        authorizationWindowFromMainWindowPaneController.setAuthorizationWindowScene(authorizationWindowScene);

        AdminWindowController authorizationWindowFromAdminWindowPaneController = (AdminWindowController) adminWindowPage.getController();
        authorizationWindowFromAdminWindowPaneController.setAuthorizationWindowScene(authorizationWindowScene);

        stage.setTitle("MyApp");
        stage.setScene(authorizationWindowScene);
        stage.show();

        new FadeIn(authorizationWindowScene.getRoot()).play();
        new FadeIn(mainWindowScene.getRoot()).play();
        new FadeIn(adminWindowScene.getRoot()).play();

    }

    @Override
    public void stop() {
        Connection.closeConnection();
    }

    public static void main(String[] args) {
        //launch();
        /*var connection = new Connection();
        connection.connectToServer("127.0.0.1", 7889);
        var connectionManager = connection.connectionManager;
        System.out.println(JBCrypt.hashpw("nktplk", JBCrypt.gensalt()));
        connectionManager.sendObject("authorize", new User("nktplk", "nikita2003", "Пользователь"));
        connectionManager.sendObject("authorize", new User("nktplk", "nikita2002", "Пользователь"));*/
        launch();
        //System.out.println(JBCrypt.hashpw("nikita2003", JBCrypt.gensalt()));



    }
}