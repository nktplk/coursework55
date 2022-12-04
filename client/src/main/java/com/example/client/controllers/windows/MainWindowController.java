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

public class MainWindowController {
    @FXML
    private BorderPane borderPane_Main;

    @FXML
    private Button button_Schedules;

    @FXML
    private Button button_tableDepartments;

    @FXML
    private Button button_tableEmployees;

    @FXML
    private Button button_tableResumes;

    @FXML
    private Button button_tableUsers;

    @FXML
    private Button button_Exit;

    @FXML
    private Button button_Chart;

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
    protected void onChartButtonClick() {
        FXMLLoader object = new FXMLLoader();
        object.setLocation(getClass().getResource("/com/example/client/charts/employeesAndResumesChart.fxml"));
        Pane view = null;
        try {
            view = object.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane_Main.setCenter(view);
        borderPane_Main.toFront();
    }

    @FXML
    protected void onTableEmployeesButtonClick() {

        FXMLLoader object = new FXMLLoader();
        object.setLocation(getClass().getResource("/com/example/client/tables/employeesTable.fxml"));
        Pane view = null;
        try {
            view = object.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane_Main.setCenter(view);
        borderPane_Main.toFront();
    }

    @FXML
    protected void onTableResumesButtonClick() {

        FXMLLoader object = new FXMLLoader();
        object.setLocation(getClass().getResource("/com/example/client/tables/resumesTable.fxml"));
        Pane view = null;
        try {
            view = object.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane_Main.setCenter(view);
        borderPane_Main.toFront();
    }

    @FXML
    protected void onTableDepartmentsButtonClick() {

        FXMLLoader object = new FXMLLoader();
        object.setLocation(getClass().getResource("/com/example/client/tables/departmentsTable.fxml"));
        Pane view = null;
        try {
            view = object.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane_Main.setCenter(view);
        borderPane_Main.toFront();
    }

    @FXML
    protected void onTableSchedulesButtonClick() {

        FXMLLoader object = new FXMLLoader();
        object.setLocation(getClass().getResource("/com/example/client/tables/SchedulesTable.fxml"));
        Pane view = null;
        try {
            view = object.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane_Main.setCenter(view);
        borderPane_Main.toFront();
    }

}