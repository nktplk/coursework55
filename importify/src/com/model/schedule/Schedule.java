package com.model.schedule;

import java.io.Serializable;

public class Schedule implements Serializable {
    private Integer ScheduleId;
    private String Schedule;

    public Schedule() {
    }

    public Schedule(Integer ScheduleId, String Schedule) {
        this.ScheduleId = ScheduleId;
        this.Schedule = Schedule;
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
}
