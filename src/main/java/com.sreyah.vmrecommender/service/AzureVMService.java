package com.sreyah.vmrecommender.service;

import com.sreyah.vmrecommender.dto.AzureVM;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AzureVMService {

    private final List<AzureVM> azureVMs = new ArrayList<>();

    public AzureVMService() {

        azureVMs.add(
                new AzureVM(
                        "Standard_B2s",
                        2,
                        4
                )
        );

        azureVMs.add(
                new AzureVM(
                        "Standard_D2s_v5",
                        2,
                        8
                )
        );

        azureVMs.add(
                new AzureVM(
                        "Standard_D4s_v5",
                        4,
                        16
                )
        );

        azureVMs.add(
                new AzureVM(
                        "Standard_D8s_v5",
                        8,
                        32
                )
        );
    }

    public AzureVM findSuitableVM(int requiredCpu, int requiredRam) {

        for (AzureVM vm : azureVMs) {

            if (vm.getCpu() >= requiredCpu &&
                    vm.getRam() >= requiredRam) {

                return vm;
            }
        }

        return azureVMs.get(azureVMs.size() - 1);
    }
}