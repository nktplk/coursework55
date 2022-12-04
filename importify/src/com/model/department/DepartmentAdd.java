package com.model.department;

import java.io.Serializable;

public class DepartmentAdd extends Department implements Serializable {
    private String name;

    public DepartmentAdd() {
    }

    public DepartmentAdd(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
