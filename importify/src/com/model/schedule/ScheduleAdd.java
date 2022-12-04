package com.model.schedule;

import java.io.Serializable;

public class ScheduleAdd extends com.model.schedule.Schedule implements Serializable {
    private String schedule;

    public ScheduleAdd() {
    }

    public ScheduleAdd(String Schedule) {
        this.schedule = Schedule;
    }


    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String Schedule) {
        this.schedule = Schedule;
    }
}
