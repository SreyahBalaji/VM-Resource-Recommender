package com.sreyah.vmrecommender.dto;

public class VMRecommendation {

    private int cpu;
    private int ram;
    private int storage;

    private String vmCategory;
    private String reason;

    private String azureVmName;
    private int azureCpu;
    private int azureRam;

    private String azureRegion;
    private double hourlyPrice;
    private double monthlyPrice;

    public VMRecommendation(
            int cpu,
            int ram,
            int storage,
            String vmCategory,
            String reason,
            String azureVmName,
            int azureCpu,
            int azureRam,
            String azureRegion,
            double hourlyPrice,
            double monthlyPrice) {

        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;

        this.vmCategory = vmCategory;
        this.reason = reason;

        this.azureVmName = azureVmName;
        this.azureCpu = azureCpu;
        this.azureRam = azureRam;

        this.azureRegion = azureRegion;
        this.hourlyPrice = hourlyPrice;
        this.monthlyPrice = monthlyPrice;
    }

    public int getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getVmCategory() {
        return vmCategory;
    }

    public String getReason() {
        return reason;
    }

    public String getAzureVmName() {
        return azureVmName;
    }

    public int getAzureCpu() {
        return azureCpu;
    }

    public int getAzureRam() {
        return azureRam;
    }

    public String getAzureRegion() {
        return azureRegion;
    }

    public double getHourlyPrice() {
        return hourlyPrice;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }
}