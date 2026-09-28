package com.sreyah.vmrecommender.controller;

import com.sreyah.vmrecommender.dto.VMRecommendation;
import com.sreyah.vmrecommender.dto.WorkloadRequest;
import com.sreyah.vmrecommender.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService) {

        this.recommendationService = recommendationService;
    }

    @PostMapping("/recommend")
    public VMRecommendation recommendVM(
            @RequestBody WorkloadRequest request) {

        return recommendationService.recommendVM(request);
    }
}