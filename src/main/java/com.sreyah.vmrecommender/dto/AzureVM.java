package com.sreyah.vmrecommender.dto;

public class AzureVM {

    private String vmName;
    private int cpu;
    private int ram;

    public AzureVM(String vmName, int cpu, int ram) {
        this.vmName = vmName;
        this.cpu = cpu;
        this.ram = ram;
    }

    public String getVmName() {
        return vmName;
    }

    public int getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }
}