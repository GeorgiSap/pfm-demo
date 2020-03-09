package com.example.pfmdemo.web.rest;

import com.example.pfmdemo.service.PortfolioManagementService;
import com.example.pfmdemo.service.dto.TimeSeriesRequestDTO;
import com.example.pfmdemo.service.dto.TimeSeriesResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PortfolioManagementResource {

    private final PortfolioManagementService service;

    public PortfolioManagementResource(PortfolioManagementService service) {
        this.service = service;
    }

    @GetMapping("/time-series")
    public List<TimeSeriesResponseDTO> calculateAssetTimeSeries(@RequestBody TimeSeriesRequestDTO requestDTO) {
        return service.calculateAssetTimeSeries(requestDTO.getTimeSeriesPrices(), requestDTO.getDates());
    }

}
