package com.example.client.controllers.charts;

import com.model.department.Department;
import com.model.employee.Employee;
import com.model.resume.Resume;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import com.example.client.connection.Connection;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class EmployeesAndResumesChartController {
    @FXML
    private AnchorPane anchorPane_ERChart;

    @FXML
    private BarChart<?, ?> barChart_Bar;

    @FXML
    private PieChart pieChart_Pie;

    @FXML
    void initialize() {
        ObservableList<Resume> dataResume;
        ObservableList<Employee> dataEmployee;
        ObservableList<Department> dataDepartment;
        dataResume = FXCollections.observableArrayList(Connection.resumesManager.getAllResumes());
        dataEmployee = FXCollections.observableArrayList(Connection.employeesManager.getAllEmployees());
        dataDepartment = FXCollections.observableArrayList(Connection.departmentsManager.getAllDepartments());
        var pieChartData = new LinkedList<PieChart.Data>();
        /*dataResume.forEach((resume) -> {
                    pieChartData.add(new PieChart.Data("Resume", resume.getResumeId()));
                }
        );
        dataEmployee.forEach((employee) -> {
                    pieChartData.add(new PieChart.Data("Employee", employee.getEmployeeId()));
                }
        );*/

        for(Department department : dataDepartment) {
            double i = 0;
            for (Employee employee : dataEmployee) {
                System.out.println(employee.getDepartmentId() == department.getDepartmentId());
                if(employee.getDepartment().equals(department.getName())) {
                    i++;
                }
            }
            pieChartData.add(new PieChart.Data(department.getName(), i));
        }
        pieChart_Pie.setData(FXCollections.observableArrayList(pieChartData));

        /*pieChartData.add(new PieChart.Data("Resume", amountResumes));
        pieChartData.add(new PieChart.Data("Employee", amountEmployees));
        pieChart_Pie.setData(FXCollections.observableArrayList(pieChartData));*/

        double amountResumes = 0;
        for(Resume resume : dataResume) {
            amountResumes++;
        }
        double amountEmployees = 0;
        for(Employee employee : dataEmployee) {
            amountEmployees++;
        }
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Resumes", amountResumes));
        set1.getData().add(new XYChart.Data("Employees", amountEmployees));
        barChart_Bar.getData().addAll(set1);
    }
}
