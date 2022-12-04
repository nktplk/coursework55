package com.model.resume;

import java.io.Serializable;

public class Resume implements Serializable {
    private Integer resumeId;
    private String name;
    private String skills;

    public Resume() {
    }

    public Resume(Integer resumeId, String name, String skills) {
        this.resumeId = resumeId;
        this.name = name;
        this.skills = skills;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
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
