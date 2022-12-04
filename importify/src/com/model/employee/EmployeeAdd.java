package com.model.employee;

import java.io.Serializable;

public class EmployeeAdd extends Employee implements Serializable {
    private String name;
    private Integer departmentId;
    private Integer resumeId;
    private Integer ScheduleId;
    public EmployeeAdd(String name, Integer departmentId, Integer resumeId, Integer ScheduleId) {
        this.name = name;
        this.departmentId = departmentId;
        this.resumeId = resumeId;
        this.ScheduleId = ScheduleId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public Integer getResumeId() {
        return resumeId;
    }

    @Override
    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getScheduleId() {
        return ScheduleId;
    }

    public void setScheduleId(Integer ScheduleId) {
        this.ScheduleId = ScheduleId;
    }
}
