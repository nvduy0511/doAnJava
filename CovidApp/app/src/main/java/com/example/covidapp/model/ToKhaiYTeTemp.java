package com.example.covidapp.model;

public class ToKhaiYTeTemp {
    private String date;
    private String time;
    private String name;

    public ToKhaiYTeTemp() {
    }

    public ToKhaiYTeTemp(String date, String time, String name) {
        this.date = date;
        this.time = time;
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
