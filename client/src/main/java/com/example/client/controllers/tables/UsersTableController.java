package com.example.client.controllers.tables;

import com.example.client.connection.Connection;
import com.hashing.JBCrypt;
import com.model.resume.Resume;
import com.model.resume.ResumeAdd;
import com.model.schedule.Schedule;
import com.model.schedule.ScheduleAdd;
import com.model.user.CurrentUser;
import com.model.user.User;
import com.model.user.UserAdd;
import com.model.user.UserTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.concurrent.TimeUnit;

public class UsersTableController {

    @FXML
    private AnchorPane anchorPane_Users;

    @FXML
    private Button button_Add;

    @FXML
    private Button button_Clear;

    @FXML
    private Button button_Delete;

    @FXML
    private Button button_Edit;

    @FXML
    private Label label_Error;

    @FXML
    private Label label_Search;

    @FXML
    private TableColumn<UserTable, Integer> tableColumn_ID;

    @FXML
    private TableColumn<UserTable, String > tableColumn_Login;

    @FXML
    private TableColumn<UserTable, String> tableColumn_Password;

    @FXML
    private TableView<UserTable> tableView_Users;

    @FXML
    private TextField textField_Login;

    @FXML
    private TextField textField_Password;

    @FXML
    private TextField textField_Role;

    @FXML
    private TextField textField_Search;

    @FXML
    private Button button_Hash;

    @FXML
    private TableColumn<UserTable, String> tableColumn_Role;

    public final ObservableList<UserTable> dataList = FXCollections.observableArrayList();

    @FXML
    void onButtonHash() {
        label_Error.setText("");
        if (textField_Password.getText().charAt(0) == '$'){
            label_Error.setText("Пароль уже зашифрован");
        } else {
            textField_Password.setText(JBCrypt.hashpw(textField_Password.getText(), JBCrypt.gensalt()));
        }
    }

    @FXML
    void initialize() {
        tableView_Users.setOnMousePressed(e ->{
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown() ){
                int index = tableView_Users.getSelectionModel().getSelectedIndex();
                System.out.println("" + index);
                textField_Login.setText(dataList.get(index).getLogin());
                textField_Password.setText(dataList.get(index).getPassword());
                textField_Role.setText(dataList.get(index).getRole());
            }
        });

        tableColumn_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumn_Login.setCellValueFactory(new PropertyValueFactory<>("login"));
        tableColumn_Password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableColumn_Role.setCellValueFactory(new PropertyValueFactory<>("role"));
        dataList.clear();
        ObservableList<UserTable> data;
        data = FXCollections.observableArrayList(Connection.usersManager.getAllUsers());
        dataList.addAll(data);
        FilteredList<UserTable> filteredData = new FilteredList<>(dataList, b -> true);
        textField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(caseCase -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(caseCase.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getLogin()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getRole()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<UserTable> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView_Users.comparatorProperty());
        tableView_Users.setItems(sortedData);
    }

    @FXML
    void addUser(ActionEvent event) {
        try {
            int i = 0;
            for (UserTable userTable : dataList) {
                if (userTable.getLogin().equals(textField_Login.getText())) {
                    i++;
                }
            }
            if (!textField_Login.getText().equals("")
                    && !textField_Password.getText().equals("")
                    && !textField_Role.getText().equals("")
                    && textField_Password.getText().charAt(0) == '$'
                    && i == 0
                    && (textField_Role.getText().equals("User") || textField_Role.getText().equals("Admin"))) {
                label_Error.setText("");
                User dataAdd = null;
                dataAdd = new UserAdd(textField_Login.getText(), textField_Password.getText(), textField_Role.getText());
                Connection.connectionManager.sendObject("addUser", dataAdd);
                TimeUnit.SECONDS.sleep(1);
                initialize();
            } else {
                label_Error.setText("Ошибка! Данные некоректны!");
            }
        } catch (InterruptedException interruptedException) {
            label_Error.setText("Ошибка!");
        }
    }
    @FXML
    private void updateUser() {
        try {
            UserTable userTable = tableView_Users.getSelectionModel().getSelectedItem();
            int i = 0;
            for (UserTable user : dataList) {
                if (user.getLogin().equals(textField_Login.getText()) && !user.getLogin().equals(userTable.getLogin())) {
                    i++;
                }
            }
            if (!textField_Login.getText().equals("")
                    && !textField_Password.getText().equals("")
                    && !textField_Role.getText().equals("")
                    && textField_Password.getText().charAt(0) == '$'
                    && (i == 0 || textField_Login.getText().equals(userTable.getLogin()))
                    && (textField_Role.getText().equals("User") || textField_Role.getText().equals("Admin"))) {
                label_Error.setText("");
                userTable.setLogin(textField_Login.getText());
                userTable.setPassword(textField_Password.getText());
                userTable.setRole(textField_Role.getText());
                Connection.connectionManager.sendObject("updateUser", userTable);
                TimeUnit.SECONDS.sleep(1);
                initialize();
            } else {
                label_Error.setText("Ошибка! Введены неккоректные параметры!");
            }
        } catch (NullPointerException nullPointerException) {
            label_Error.setText("Ошибка! Выберите запись!");
        } catch (InterruptedException interruptedException) {
            label_Error.setText("Ошибка!");
        }
    }
    @FXML
    private void deleteUser() {
        label_Error.setText("");
        try {
            UserTable userTable = tableView_Users.getSelectionModel().getSelectedItem();
            if(userTable.getLogin().equals(CurrentUser.getUser().getLogin())) {
                label_Error.setText("Вы не можете удалить себя!");
                return;
            }
            Connection.connectionManager.sendObject("deleteUser", String.valueOf(userTable.getId()));
            TimeUnit.SECONDS.sleep(1);
            initialize();
        } catch (NullPointerException nullPointerException) {
            label_Error.setText("Ошибка! Выберите элемент для удаления!");
        } catch (InterruptedException interruptedException) {
            label_Error.setText("Ошибка!");
        }
    }
    @FXML
    private void clearData() {
        textField_Search.clear();
        textField_Login.clear();
        textField_Password.clear();
        textField_Role.clear();
    }
}
