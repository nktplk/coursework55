package com.example.client.controllers.tables;

import com.example.client.connection.Connection;
import com.model.employee.Employee;
import com.model.resume.Resume;
import com.model.schedule.Schedule;
import com.model.department.Department;
import com.model.department.DepartmentAdd;
import com.model.schedule.ScheduleAdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.concurrent.TimeUnit;

public class SchedulesTableController {

    @FXML
    private AnchorPane anchorPane_Schedules;

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
    private TableColumn<Schedule, Integer> tableColumn_ID;

    @FXML
    private TableColumn<Schedule, String> tableColumn_Schedule;

    @FXML
    private TableView<Schedule> tableView_Schedules;

    @FXML
    private TextField textField_Search;

    @FXML
    private TextField textField_Schedule;
    @FXML
    private Label label_Error;
    public final ObservableList<Schedule> dataList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        /*EventHandler<MouseEvent> clickListener = evt -> {
            TableRow<Schedule> row = (TableRow<Schedule>) evt.getTarget();
            if (!row.isEmpty()) {
                // do something for non-empty rows
                System.out.println("you clicked " + row.getItem());
                textField_Schedule.setText(row.getItem().getSchedule());
            }
        };
        tableView_Schedules.setRowFactory(tv -> {
            TableRow<Schedule> row = new TableRow<>();
            // add click listener to row
            row.setOnMouseClicked(clickListener);
            return row;
        });*/
        tableView_Schedules.setOnMousePressed(e ->{
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown() ){
                int index = tableView_Schedules.getSelectionModel().getSelectedIndex();
                System.out.println("" + index);
                textField_Schedule.setText(dataList.get(index).getSchedule());
            }
        });

        tableColumn_ID.setCellValueFactory(new PropertyValueFactory<>("ScheduleId"));
        tableColumn_Schedule.setCellValueFactory(new PropertyValueFactory<>("Schedule"));
        dataList.clear();
        ObservableList<Schedule> data;
        data = FXCollections.observableArrayList(Connection.SchedulesManager.getAllSchedules());
        dataList.addAll(data);
        FilteredList<Schedule> filteredData = new FilteredList<>(dataList, b -> true);
        textField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(caseCase -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(caseCase.getScheduleId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getSchedule()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Schedule> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView_Schedules.comparatorProperty());
        tableView_Schedules.setItems(sortedData);
    }
    @FXML
    void addSchedule(ActionEvent event) {
        try {
            if (!textField_Schedule.getText().equals("")) {
                label_Error.setText("");
                Schedule dataAdd = null;
                dataAdd = new ScheduleAdd(textField_Schedule.getText());
                Connection.connectionManager.sendObject("addSchedule", dataAdd);
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
    private void updateSchedule() {
        try {
            if (!textField_Schedule.getText().equals("")) {
                label_Error.setText("");
                Schedule schedule = tableView_Schedules.getSelectionModel().getSelectedItem();
                schedule.setSchedule(textField_Schedule.getText());
                Connection.connectionManager.sendObject("updateSchedule", schedule);
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
    private void deleteSchedule() {
        try {
            Schedule schedule = tableView_Schedules.getSelectionModel().getSelectedItem();
            label_Error.setText("");
            Connection.connectionManager.sendObject("deleteSchedule", String.valueOf(schedule.getScheduleId()));
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
        textField_Schedule.clear();
    }
}