package com.example.client.controllers.charts;

import com.example.client.connection.Connection;
import com.model.department.Department;
import com.model.employee.Employee;
import com.model.resume.Resume;
import com.model.user.User;
import com.model.user.UserTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedList;

public class UsersChartController {
    @FXML
    private AnchorPane anchorPane_ERChart;

    @FXML
    private BarChart<?, ?> barChart_Bar;

    @FXML
    private PieChart pieChart_Pie;



    @FXML
    void initialize() {
        ObservableList<UserTable> dataUser;
        dataUser = FXCollections.observableArrayList(Connection.usersManager.getAllUsers());
        var pieChartData = new LinkedList<PieChart.Data>();
        /*dataResume.forEach((resume) -> {
                    pieChartData.add(new PieChart.Data("Resume", resume.getResumeId()));
                }
        );
        dataEmployee.forEach((employee) -> {
                    pieChartData.add(new PieChart.Data("Employee", employee.getEmployeeId()));
                }
        );*/

        double amountUsers = 0;
        double amountAdmins = 0;

        for (UserTable userTable : dataUser) {
            if (userTable.getRole().equals("Admin")) {
                amountAdmins++;
            } else {
                amountUsers++;
            }
        }


        pieChartData.add(new PieChart.Data("Users", amountUsers));
        pieChartData.add(new PieChart.Data("Admins", amountAdmins));
        pieChart_Pie.setData(FXCollections.observableArrayList(pieChartData));

        /*pieChartData.add(new PieChart.Data("Resume", amountResumes));
        pieChartData.add(new PieChart.Data("Employee", amountEmployees));
        pieChart_Pie.setData(FXCollections.observableArrayList(pieChartData));*/

    }
}
