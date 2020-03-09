package com.example.pfmdemo.web.rest;

import com.example.pfmdemo.service.PortfolioManagementService;
import com.example.pfmdemo.service.dto.TimeSeriesRequestDTO;
import com.example.pfmdemo.service.dto.TimeSeriesResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PortfolioManagementResource {

    private final Logger log = LoggerFactory.getLogger(PortfolioManagementResource.class);

    private final PortfolioManagementService service;

    public PortfolioManagementResource(PortfolioManagementService service) {
        this.service = service;
    }

    @GetMapping("portfolio-managements/time-series")
    public ResponseEntity<List<TimeSeriesResponseDTO>> calculateAssetTimeSeries(@RequestBody TimeSeriesRequestDTO requestDTO) {
        log.debug("REST request to calculate time series of a portfolio : {}", requestDTO);
        List<TimeSeriesResponseDTO> result = service.calculateAssetTimeSeries(requestDTO.getTimeSeriesPrices(), requestDTO.getDates());
        return ResponseEntity.ok().body(result);
    }

}
