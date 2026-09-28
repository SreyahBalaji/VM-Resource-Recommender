package com.sreyah.vmrecommender.dto;

public class WorkloadRequest {

    private String workloadType;
    private int numberOfUsers;
    private String workloadIntensity;
    private boolean databaseRequired;
    private int storageRequired;

    public WorkloadRequest() {
    }

    public String getWorkloadType() {
        return workloadType;
    }

    public void setWorkloadType(String workloadType) {
        this.workloadType = workloadType;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public String getWorkloadIntensity() {
        return workloadIntensity;
    }

    public void setWorkloadIntensity(String workloadIntensity) {
        this.workloadIntensity = workloadIntensity;
    }

    public boolean isDatabaseRequired() {
        return databaseRequired;
    }

    public void setDatabaseRequired(boolean databaseRequired) {
        this.databaseRequired = databaseRequired;
    }

    public int getStorageRequired() {
        return storageRequired;
    }

    public void setStorageRequired(int storageRequired) {
        this.storageRequired = storageRequired;
    }
}