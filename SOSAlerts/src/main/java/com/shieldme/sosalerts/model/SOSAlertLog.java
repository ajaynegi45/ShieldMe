package com.shieldme.sosalerts.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "sos_alerts_log")
public class SOSAlertLog {

    @Id
    private ObjectId alertId;

    @Indexed(unique = true)
    private ObjectId userId;

    private LocalDateTime timestamp;
    private double latitude;
    private double longitude;
    private String status;

    public SOSAlertLog() {
    }

    public SOSAlertLog(ObjectId alertId, ObjectId userId, LocalDateTime timestamp, double latitude, double longitude, String status) {
        this.alertId = alertId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public ObjectId getAlertId() {
        return alertId;
    }

    public void setAlertId(ObjectId alertId) {
        this.alertId = alertId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

