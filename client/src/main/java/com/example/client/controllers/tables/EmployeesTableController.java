package com.example.client.controllers.tables;

import com.example.client.connection.Connection;
import com.model.employee.Employee;
import com.model.employee.EmployeeAdd;
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

public class EmployeesTableController {
    @FXML
    private AnchorPane anchorPane_Employees;

    @FXML
    private Label label_Search;
    @FXML
    private Label label_Error;

    @FXML
    private TableView<Employee> tableView_Employees;
    @FXML
    private TableColumn<Employee, String> tableColumn_Department;
    @FXML
    private TableColumn<Employee, Integer> tableColumn_ID;
    @FXML
    private TableColumn<Employee, Integer> tableColumn_IDResume;
    @FXML
    private TableColumn<Employee, String> tableColumn_Name;
    @FXML
    private TableColumn<Employee, String> tableColumn_Skills;
    @FXML
    private TableColumn<Employee, Integer> tableColumn_IDDepartment;
    @FXML
    private TableColumn<Employee, Integer> tableColumn_IDSchedule;
    @FXML
    private TableColumn<Employee, String> tableColumn_Schedule;

    @FXML
    private TextField textField_Search;
    @FXML
    private TextField textField_Name;
    @FXML
    private TextField textField_IDDepartment;
    @FXML
    private TextField textField_IDResume;
    @FXML
    private TextField textField_IDSchedule;

    @FXML
    private Button button_Add;
    @FXML
    private Button button_Clear;
    @FXML
    private Button button_Delete;
    @FXML
    private Button button_Edit;

    public final ObservableList<Employee> dataList = FXCollections.observableArrayList();

    //Добавление записи "Сотрудник" в таблицу
    @FXML
    void addEmployee(ActionEvent event) {
        try {
            if (!textField_Name.getText().equals("")
                    && !textField_IDResume.getText().equals("")
                    && !textField_IDDepartment.getText().equals("")
                    && !textField_IDSchedule.getText().equals("")) {
                label_Error.setText("");
                Employee dataAdd = null;
                dataAdd = new EmployeeAdd(textField_Name.getText(),
                        Integer.parseInt(textField_IDDepartment.getText()),
                        Integer.parseInt(textField_IDResume.getText()),
                        Integer.parseInt(textField_IDSchedule.getText()));
                Connection.connectionManager.sendObject("addEmployee", dataAdd);
                TimeUnit.SECONDS.sleep(1);
                initialize();
            } else {
                label_Error.setText("Ошибка! Введены не все параметры для добавления!");
            }
        } catch (NumberFormatException numberFormatException) {
            label_Error.setText("Ошибка! Введите корректные данные!");
        } catch (InterruptedException interruptedException) {
            label_Error.setText("Ошибка!");
        }
    }

    //Удаление записи "Сорудник" из таблицы
    @FXML
    private void deleteEmployee() {
        try {
            Employee employee = tableView_Employees.getSelectionModel().getSelectedItem();
            label_Error.setText("");
            Connection.connectionManager.sendObject("deleteEmployee", String.valueOf(employee.getEmployeeId()));
            TimeUnit.SECONDS.sleep(1);
            initialize();
        } catch (NullPointerException nullPointerException) {
            label_Error.setText("Ошибка! Выберите элемент для удаления!");
        } catch (InterruptedException interruptedException) {
            label_Error.setText("Ошибка!");
        }
    }

    //Изменение записи "Сотрудник" в таблице
    @FXML
    private void updateEmployee() {
        try {
            if (!textField_Name.getText().equals("")
                    && !textField_IDResume.getText().equals("")
                    && !textField_IDDepartment.getText().equals("")
                    && !textField_IDSchedule.getText().equals("")) {
                label_Error.setText("");
                Employee employee = tableView_Employees.getSelectionModel().getSelectedItem();
                employee.setName(textField_Name.getText());
                employee.setResumeId(Integer.parseInt(textField_IDResume.getText()));
                employee.setDepartmentId(Integer.parseInt(textField_IDDepartment.getText()));
                employee.setScheduleId(Integer.parseInt(textField_IDSchedule.getText()));
                Connection.connectionManager.sendObject("updateEmployee", employee);
                TimeUnit.SECONDS.sleep(1);
                initialize();
            } else {
                label_Error.setText("Ошибка! Введены не все параметры для изменения!");
            }
        } catch (NullPointerException nullPointerException) {
            label_Error.setText("Ошибка! Выберите запись!");
        } catch (NumberFormatException numberFormatException) {
            label_Error.setText("Ошибка! Введите корректные данные!");
        } catch (InterruptedException interruptedException) {
            label_Error.setText("Ошибка!");
        }
    }

    //Отчищение полей для ввода
    @FXML
    private void clearData() {
        textField_Search.clear();
        textField_Name.clear();
        textField_IDResume.clear();
        textField_IDDepartment.clear();
        textField_IDSchedule.clear();
    }

    //Инициализация и поиск по таблице
    @FXML
    void initialize() {
        tableView_Employees.setOnMousePressed(e ->{
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown() ){
                int index = tableView_Employees.getSelectionModel().getSelectedIndex();
                System.out.println("" + index);
                textField_Name.setText(dataList.get(index).getName());
                textField_IDResume.setText(dataList.get(index).getResumeId().toString());
                textField_IDDepartment.setText(dataList.get(index).getDepartmentId().toString());
                textField_IDSchedule.setText(dataList.get(index).getScheduleId().toString());
            }
        });

        tableColumn_ID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tableColumn_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn_IDResume.setCellValueFactory(new PropertyValueFactory<>("resumeId"));
        tableColumn_Skills.setCellValueFactory(new PropertyValueFactory<>("skills"));
        tableColumn_IDDepartment.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        tableColumn_Department.setCellValueFactory(new PropertyValueFactory<>("department"));
        tableColumn_IDSchedule.setCellValueFactory(new PropertyValueFactory<>("ScheduleId"));
        tableColumn_Schedule.setCellValueFactory(new PropertyValueFactory<>("Schedule"));
        ObservableList<Employee> data;
        data = FXCollections.observableArrayList(Connection.employeesManager.getAllEmployees());
        dataList.clear();
        dataList.addAll(data);
        FilteredList<Employee> filteredData = new FilteredList<>(dataList, b -> true);
        textField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(caseCase -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(caseCase.getEmployeeId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getName()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getResumeId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getSkills()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getDepartmentId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getDepartment()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getScheduleId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getSchedule()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView_Employees.comparatorProperty());
        tableView_Employees.setItems(sortedData);
    }
}