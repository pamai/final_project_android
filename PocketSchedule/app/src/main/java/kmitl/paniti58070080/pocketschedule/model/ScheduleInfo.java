package kmitl.paniti58070080.pocketschedule.model;


import android.support.annotation.NonNull;

import java.time.LocalTime;

public class ScheduleInfo implements Comparable<ScheduleInfo>{

    private String day;
    private String time_start;
    private String time_end;
    private String location;
    private String subject;
    private String key;

    public ScheduleInfo() {
    }

    public ScheduleInfo(String day, String time_start, String time_end, String location, String subject, String key) {

        this.day = day;
        this.time_start = time_start;
        this.time_end = time_end;
        this.location = location;
        this.subject = subject;
        this.key = key;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(@NonNull ScheduleInfo scheduleInfo) {
        return 0;
    }
}
