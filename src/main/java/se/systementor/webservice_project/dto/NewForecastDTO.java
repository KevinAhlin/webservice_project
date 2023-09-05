package se.systementor.webservice_project.dto;

public class NewForecastDTO {   // Data Transfer Object - 'box' with properties

    public int Date; // 20230101
    public int Hour;
    public float Temperature;


    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public float getTemperature() {
        return Temperature;
    }

    public void setTemperature(float temperature) {
        Temperature = temperature;
    }
}
