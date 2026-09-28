package com.sreyah.vmrecommender.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class AzurePriceService {

    private static final String BASE_URL =
            "https://prices.azure.com/api/retail/prices";

    public double getHourlyPrice(String vmName) {

        try {

            String filter =
                    "serviceName eq 'Virtual Machines'" +
                            " and armSkuName eq '" + vmName + "'" +
                            " and armRegionName eq 'centralindia'" +
                            " and priceType eq 'Consumption'";

            String encodedFilter =
                    URLEncoder.encode(
                            filter,
                            StandardCharsets.UTF_8
                    ).replace("+", "%20");

            String url =
                    BASE_URL + "?$filter=" + encodedFilter;

            RestTemplate restTemplate = new RestTemplate();

            Map response =
                    restTemplate.getForObject(
                            URI.create(url),
                            Map.class
                    );

            if (response == null) {
                return -1;
            }

            List<Map<String, Object>> items =
                    (List<Map<String, Object>>) response.get("Items");

            if (items == null || items.isEmpty()) {
                return -1;
            }

            // Prefer normal Linux consumption pricing
            for (Map<String, Object> item : items) {

                String productName =
                        String.valueOf(item.get("productName"));

                String meterName =
                        String.valueOf(item.get("meterName"));

                String type =
                        String.valueOf(item.get("type"));

                if ("Consumption".equalsIgnoreCase(type)
                        && !productName.toLowerCase().contains("windows")
                        && !meterName.toLowerCase().contains("spot")) {

                    Object price =
                            item.get("retailPrice");

                    if (price instanceof Number) {
                        return ((Number) price).doubleValue();
                    }
                }
            }

            return -1;

        } catch (Exception e) {

            System.out.println(
                    "Azure price could not be retrieved: "
                            + e.getMessage()
            );

            return -1;
        }
    }
}