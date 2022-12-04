package com.example.client.controllers.tables;

import com.example.client.connection.Connection;
import com.model.employee.Employee;
import com.model.employee.EmployeeAdd;
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

public class ResumesTableController {

    @FXML
    private AnchorPane anchorPane_Resumes;

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
    private TableColumn<Resume, Integer> tableColumn_ID;

    @FXML
    private TableView<Resume> tableView_Resumes;

    @FXML
    private TableColumn<Resume, Integer> tableColumn_Name;

    @FXML
    private TableColumn<Resume, Integer> tableColumn_Skills;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_Search;

    @FXML
    private Label label_Error;


    @FXML
    private TextField textField_Skills;

    public final ObservableList<Resume> dataList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        tableView_Resumes.setOnMousePressed(e ->{
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown() ){
                int index = tableView_Resumes.getSelectionModel().getSelectedIndex();
                System.out.println("" + index);
                textField_Name.setText(dataList.get(index).getName());
                textField_Skills.setText(dataList.get(index).getSkills());
            }
        });

        tableColumn_ID.setCellValueFactory(new PropertyValueFactory<>("resumeId"));
        tableColumn_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn_Skills.setCellValueFactory(new PropertyValueFactory<>("skills"));
        dataList.clear();
        ObservableList<Resume> data;
        data = FXCollections.observableArrayList(Connection.resumesManager.getAllResumes());
        dataList.addAll(data);
        FilteredList<Resume> filteredData = new FilteredList<>(dataList, b -> true);
        textField_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(caseCase -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(caseCase.getResumeId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getName()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(caseCase.getSkills()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Resume> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView_Resumes.comparatorProperty());
        tableView_Resumes.setItems(sortedData);
    }

    @FXML
    void addResume(ActionEvent event) {
        try {
            if (!textField_Name.getText().equals("") && !textField_Skills.getText().equals("")) {
                label_Error.setText("");
                Resume dataAdd = null;
                dataAdd = new ResumeAdd(textField_Name.getText(), textField_Skills.getText());
                Connection.connectionManager.sendObject("addResume", dataAdd);
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
    private void updateResume() {
        try {
            if (!textField_Name.getText().equals("")
                    && !textField_Skills.getText().equals("")) {
                label_Error.setText("");
                Resume resume = tableView_Resumes.getSelectionModel().getSelectedItem();
                resume.setName(textField_Name.getText());
                resume.setSkills(textField_Skills.getText());
                Connection.connectionManager.sendObject("updateResume", resume);
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
    private void deleteResume() {
        try {
            Resume resume = tableView_Resumes.getSelectionModel().getSelectedItem();
            label_Error.setText("");
            Connection.connectionManager.sendObject("deleteResume", String.valueOf(resume.getResumeId()));
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
        textField_Skills.clear();
    }
}
