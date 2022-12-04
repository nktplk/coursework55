package com.example.client.controllers.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminWindowController {
    @FXML
    private BorderPane borderPane_Admin;

    @FXML
    private Button button_Exit;

    @FXML
    private Button button_Chart;

    @FXML
    private Button button_tableUsers;

    private Scene authorizationWindowScene;
    public void setAuthorizationWindowScene(Scene scene) {
        authorizationWindowScene = scene;
    }

    @FXML
    protected void onExitButtonClick(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(authorizationWindowScene);
    }

    @FXML
    protected void onChartButtonClick(ActionEvent event) {
        FXMLLoader object = new FXMLLoader();
        object.setLocation(getClass().getResource("/com/example/client/charts/usersChart.fxml"));
        Pane view = null;
        try {
            view = object.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane_Admin.setCenter(view);
        borderPane_Admin.toFront();
    }

    @FXML
    protected void onTableUsersButtonClick() {

        FXMLLoader object = new FXMLLoader();
        object.setLocation(getClass().getResource("/com/example/client/tables/usersTable.fxml"));
        Pane view = null;
        try {
            view = object.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane_Admin.setCenter(view);
        borderPane_Admin.toFront();
    }


}