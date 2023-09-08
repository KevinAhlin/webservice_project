package se.systementor.webservice_project.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Forecast {

    private UUID id;
    //private int date;
    //private int hour;
    private LocalDateTime created;
    private LocalDateTime updated;
    private float longitude;
    private float latitude;
    private LocalDate predictionDate; // 20240101
    private int predictionHour;
    private int predictionTemperature;
    private boolean rainOrSnow;
    //private ApiProvider apiProvider;
    //private float temperature;
    //private String lastModifiedBy;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public LocalDate getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(LocalDate predictionDate) {
        this.predictionDate = predictionDate;
    }

    public int getPredictionHour() {
        return predictionHour;
    }

    public void setPredictionHour(int predictionHour) {
        this.predictionHour = predictionHour;
    }

    public int getPredictionTemperature() {
        return predictionTemperature;
    }

    public void setPredictionTemperature(int predictionTemperature) {
        this.predictionTemperature = predictionTemperature;
    }

    public boolean isRainOrSnow() {
        return rainOrSnow;
    }

    public void setRainOrSnow(boolean rainOrSnow) {
        this.rainOrSnow = rainOrSnow;
    }

    /*
    public int getDate() {
        return date;
    }

    public void setDate(int day) {
        this.date = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

     */

}
