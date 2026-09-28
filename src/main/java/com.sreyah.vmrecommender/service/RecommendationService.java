package com.sreyah.vmrecommender.service;

import com.sreyah.vmrecommender.dto.VMRecommendation;
import com.sreyah.vmrecommender.dto.WorkloadRequest;
import org.springframework.stereotype.Service;
import com.sreyah.vmrecommender.dto.AzureVM;
@Service
public class RecommendationService {
    private final AzureVMService azureVMService;
    private final AzurePriceService azurePriceService;

    public RecommendationService(
            AzureVMService azureVMService,
            AzurePriceService azurePriceService) {

        this.azureVMService = azureVMService;
        this.azurePriceService = azurePriceService;
    }

    public VMRecommendation recommendVM(WorkloadRequest request) {

        int score = 0;

        // 1. Number of users
        if (request.getNumberOfUsers() <= 100) {
            score += 1;
        } else if (request.getNumberOfUsers() <= 1000) {
            score += 2;
        } else {
            score += 3;
        }

        // 2. Workload intensity
        if (request.getWorkloadIntensity().equalsIgnoreCase("Low")) {
            score += 1;
        } else if (request.getWorkloadIntensity().equalsIgnoreCase("Medium")) {
            score += 2;
        } else if (request.getWorkloadIntensity().equalsIgnoreCase("High")) {
            score += 3;
        }

        // 3. Workload type
        String type = request.getWorkloadType();

        if (type.equalsIgnoreCase("Development")) {
            score += 1;
        } else if (type.equalsIgnoreCase("Web Application")) {
            score += 2;
        } else if (type.equalsIgnoreCase("Database")) {
            score += 3;
        } else if (type.equalsIgnoreCase("Data Processing")) {
            score += 3;
        }

        // 4. Database requirement
        if (request.isDatabaseRequired()) {
            score += 2;
        }

        // 5. Storage requirement
        if (request.getStorageRequired() < 50) {
            score += 1;
        } else if (request.getStorageRequired() <= 200) {
            score += 2;
        } else {
            score += 3;
        }

        int cpu;
        int ram;
        int storage;
        String category;

        // Final VM recommendation
        if (score <= 6) {

            cpu = 2;
            ram = 4;
            storage = Math.max(50, request.getStorageRequired());
            category = "Light VM";

        } else if (score <= 10) {

            cpu = 4;
            ram = 8;
            storage = Math.max(100, request.getStorageRequired());
            category = "Medium VM";

        } else {

            cpu = 8;
            ram = 16;
            storage = Math.max(250, request.getStorageRequired());
            category = "High Performance VM";
        }

        String reason =
                "This configuration is recommended for a "
                        + request.getWorkloadIntensity()
                        + " intensity "
                        + request.getWorkloadType()
                        + " workload with "
                        + request.getNumberOfUsers()
                        + " expected users and "
                        + request.getStorageRequired()
                        + " GB requested storage.";

        if (request.isDatabaseRequired()) {
            reason += " Additional resources are allocated because database support is required.";
        }
        AzureVM azureVM =
                azureVMService.findSuitableVM(cpu, ram);

        double hourlyPrice =
                azurePriceService.getHourlyPrice(
                        azureVM.getVmName()
                );

        double monthlyPrice;

        if (hourlyPrice >= 0) {

            // Approximate 730 hours in one month
            monthlyPrice = hourlyPrice * 730;

            hourlyPrice =
                    Math.round(hourlyPrice * 10000.0) / 10000.0;

            monthlyPrice =
                    Math.round(monthlyPrice * 100.0) / 100.0;

        } else {

            monthlyPrice = -1;
        }

        return new VMRecommendation(
                cpu,
                ram,
                storage,
                category,
                reason,
                azureVM.getVmName(),
                azureVM.getCpu(),
                azureVM.getRam(),
                "Central India",
                hourlyPrice,
                monthlyPrice
        );
    }
}
