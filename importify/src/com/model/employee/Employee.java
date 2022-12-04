package com.model.employee;

import java.io.Serializable;

public class Employee implements Serializable {
    private Integer employeeId;
    private String name;
    private Integer resumeId;
    private String skills;
    private Integer departmentId;
    private String department;
    private Integer ScheduleId;
    private String Schedule;

    public Employee() {
    }

    public Employee(Integer employeeId, String name, Integer resumeId, String skills, Integer departmentId, String department, Integer ScheduleId, String Schedule) {
        this.employeeId = employeeId;
        this.name = name;
        this.resumeId = resumeId;
        this.skills = skills;
        this.departmentId = departmentId;
        this.department = department;
        this.ScheduleId = ScheduleId;
        this.Schedule = Schedule;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getScheduleId() {
        return ScheduleId;
    }

    public void setScheduleId(Integer ScheduleId) {
        this.ScheduleId = ScheduleId;
    }

    public String getSchedule() {
        return Schedule;
    }

    public void setSchedule(String Schedule) {
        this.Schedule = Schedule;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee1 employee = (Employee1)o;
        return employeeId == employee.employeeId
                && name == employee.name
                && department == employee.department
                && resumeId == employee.resumeId
                && skills == employee.skills
                && Objects.equals(employeeId, employee.employeeId)
                && Objects.equals(name, employee.name)
                && Objects.equals(department, employee.department)
                && Objects.equals(resumeId, employee.resumeId)
                && Objects.equals(skills, employee.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, department, resumeId, skills);
    }*/
}
