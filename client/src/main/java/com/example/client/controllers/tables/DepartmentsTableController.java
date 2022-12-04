package com.example.client.controllers.tables;

import com.example.client.connection.Connection;
import com.model.department.Department;
import com.model.department.DepartmentAdd;
import com.model.employee.Employee;
import com.model.resume.Resume;
import com.model.resume.ResumeAdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.concurrent.TimeUnit;

public class DepartmentsTableController {

    @FXML
    private AnchorPane anchorPane_Departments;

    @FXML
    private Button button_Add;

    @FXML
    private Button button_Clear;

    @FXML
    private Button button_Delete;

    @FXML
    private Button button_Edit;

    @FXML
    private Label label_Search;

    @FXML
    private TableColumn<Department, Integer> tableColumn_ID;

    @FXML
    private TableColumn<Department, String> tableColumn_Name;

    @FXML
    private TableView<Department> tableView_Departments;

    @FXML
    private Label label_Error;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_Search;

    public final ObservableList<Department> dataList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        tableView_Departments.setOnMousePressed(e ->{
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown() ){
                int index = tableView_Departments.getSelectionModel().getSelectedIndex();
                System.out.println("" + index);
                textField_Name.setText(dataList.get(index).getName());
            }
        });


        tableColumn_ID.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        tableColumn_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dataList.clear();
        ObservableList<Department> data;
        data = FXCollections.observableArrayList(Connection.departmentsManager.getAllDepartments());
        dataList.addAll(data);
        FilteredList<Department> filteredData = new FilteredList<>(dataList, b -> true);
        textField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(caseCase -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(caseCase.getDepartmentId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getName()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Department> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView_Departments.comparatorProperty());
        tableView_Departments.setItems(sortedData);
    }
    @FXML
    void addDepartment(ActionEvent event) {
        try {
            if (!textField_Name.getText().equals("")) {
                label_Error.setText("");
                Department dataAdd = null;
                dataAdd = new DepartmentAdd(textField_Name.getText());
                Connection.connectionManager.sendObject("addDepartment", dataAdd);
                TimeUnit.SECONDS.sleep(1);
                initialize();
            } else {
                label_Error.setText("Ошибка! Введены не все параметры для добавления!");
            }
        } catch (InterruptedException interruptedException) {
            label_Error.setText("Ошибка!");
        }
    }
    @FXML
    private void updateDepartment() {
        try {
            if (!textField_Name.getText().equals("")) {
                label_Error.setText("");
                Department department = tableView_Departments.getSelectionModel().getSelectedItem();
                department.setName(textField_Name.getText());
                Connection.connectionManager.sendObject("updateDepartment", department);
                TimeUnit.SECONDS.sleep(1);
                initialize();
            } else {
                label_Error.setText("Ошибка! Введены не все параметры для изменения!");
            }
        } catch (NullPointerException nullPointerException) {
            label_Error.setText("Ошибка! Выберите запись!");
        } catch (InterruptedException interruptedException) {
            label_Error.setText("Ошибка!");
        }
    }
    @FXML
    private void deleteDepartment() {
        try {
            Department department = tableView_Departments.getSelectionModel().getSelectedItem();
            label_Error.setText("");
            Connection.connectionManager.sendObject("deleteDepartment", String.valueOf(department.getDepartmentId()));
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
        textField_Name.clear();
    }
}