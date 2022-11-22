package com.example.client;


import animatefx.animation.FadeIn;
import com.example.client.connection.Connection;
import com.example.client.controllers.ConnectionToServerController;
import com.model.user.User;
import com.hashing.JBCrypt;
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
//        fxmlLoader.setLocation(getClass().getResource("connection-to-server-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
//        stage.setTitle("My App");
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader firstPage = new FXMLLoader();
        FXMLLoader secondPage = new FXMLLoader();
        firstPage.setLocation(getClass().getResource("connection-to-server-view.fxml"));
        secondPage.setLocation(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(firstPage.load(), 1200, 800);
        Parent secondPane = secondPage.load();
        Scene secondScene = new Scene(secondPane, 1200, 800);

        ConnectionToServerController firstPaneController = (ConnectionToServerController) firstPage.getController();
        firstPaneController.setSecondScene(secondScene);

        stage.setTitle("MyApp");
        stage.setScene(scene);
        stage.show();

        new FadeIn(scene.getRoot()).play();
        new FadeIn(secondScene.getRoot()).play();

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