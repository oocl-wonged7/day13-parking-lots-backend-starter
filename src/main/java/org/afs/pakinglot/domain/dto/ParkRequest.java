package org.afs.pakinglot.domain.dto;

public class ParkRequest {
    private String plateNumber;
    private String strategy;

    // Getters and setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}