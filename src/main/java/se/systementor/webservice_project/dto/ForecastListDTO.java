package se.systementor.webservice_project.dto;

import java.util.UUID;

public class ForecastListDTO {
    // Class for what info to show users when it's a list

    public UUID Id;
    public int Date;
    public int Hour;
    public float Temperature;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

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
