package com.model.resume;

import java.io.Serializable;

public class ResumeAdd extends Resume implements Serializable {
    private String name;
    private String skills;

    public ResumeAdd() {
    }

    public ResumeAdd(String name, String skills) {
        this.name = name;
        this.skills = skills;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
