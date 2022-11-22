package com.example.client.controllers;

import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideOutLeft;
import com.example.client.connection.Connection;
import com.example.client.connection.ConnectionManager;
import com.model.user.CurrentUser;
import com.model.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

public class ConnectionToServerController {

    @FXML
    private AnchorPane anchorPane_Main;

    @FXML
    private Button button_Connect;

    @FXML
    private Button button_SignIn;

    @FXML
    private Button button_SignUp;

    @FXML
    private Label label_Connection;

    @FXML
    private Label label_ConnectionFailed;

    @FXML
    private Label label_SignIn;

    @FXML
    private Label label_SignIn1;

    @FXML
    private Label label_SignInFailed;

    @FXML
    private Label label_SignUpFailed;

    @FXML
    private Pane pane_Connection;

    @FXML
    private Pane pane_SignIn;

    @FXML
    private Pane pane_SignUp;

    @FXML
    private TextField textField_IPAdress;

    @FXML
    private TextField textField_Port;

    @FXML
    private TextField textField_SignInLogin;

    @FXML
    private TextField textField_SignInPassword;

    @FXML
    private TextField textField_SignUpLogin;

    @FXML
    private TextField textField_SignUpPassword;

    @FXML
    private TextField textField_SignUpRepeatPassword;


    private ConnectionManager connectionManager;
    private Scene secondScene;
    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }

    @FXML
    private void completeSignUp(ActionEvent event) {
        if (!textField_SignUpPassword.getText().equals(textField_SignUpRepeatPassword.getText())) {
            label_SignUpFailed.setText("Введенные пароли не совпадают");
            label_SignUpFailed.setVisible(true);
            return;
        }
        connectionManager.sendObject("regist", new User(textField_SignUpLogin.getText(), textField_SignUpPassword.getText(), "User"));
        if(CurrentUser.getInstance((User)connectionManager.readObject()) != null) {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(secondScene);
        }
        else {
            CurrentUser.nullInstance();
            /*if (connectionManager.readString() == "exists") {
                label_SignUpFailed.setText("Данный логин уже существует");
                label_SignUpFailed.setVisible(true);
            }
            else {
                CurrentUser.nullInstance();
                label_SignUpFailed.setVisible(true);
            }*/
            label_SignUpFailed.setText("Данный логин уже существует");
            label_SignUpFailed.setVisible(true);
        }
    }

    @FXML
    private void completeSignIn(ActionEvent event) {
        connectionManager.sendObject("authorize",
                new User(textField_SignInLogin.getText(),
                        textField_SignInPassword.getText(),
                        "Пользователь"));
        if (CurrentUser.getInstance((User)connectionManager.readObject()) != null) {
            System.out.println("good");
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(secondScene);
        }
        else {
            CurrentUser.nullInstance();
            label_SignInFailed.setVisible(true);
        }

    }

    @FXML
    private void connect() {
        var connection = new Connection();
        if (textField_Port.getText().isEmpty() == false
                && textField_IPAdress.getText().isEmpty() == false
                && textField_Port.getText().matches("[1-9]+")) {
            connection.connectToServer(
                    textField_IPAdress.getText(), Integer.parseInt(textField_Port.getText())
            );
            connectionManager = connection.connectionManager;

            if (connectionManager != null) {
                label_ConnectionFailed.setVisible(false);
                new SlideOutLeft(pane_Connection).play();
                new SlideInLeft(pane_SignIn).play();
                pane_Connection.toBack();
                pane_SignIn.toFront();
                button_SignUp.setDisable(false);
                button_SignIn.setDisable(false);
            }
            else {
                label_ConnectionFailed.setVisible(true);
                label_ConnectionFailed.toFront();
            }
        }
        else {
            label_ConnectionFailed.setVisible(true);
            label_ConnectionFailed.toFront();
        }
    }

}

