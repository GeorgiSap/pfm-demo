package com.example.pfmdemo.web.rest;

import com.example.pfmdemo.service.PortfolioManagementService;
import com.example.pfmdemo.service.dto.TimeSeriesRequestDTO;
import com.example.pfmdemo.service.dto.TimeSeriesResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        if (!requestDTO.getTimeSeriesPrices().stream().map(List::size).allMatch(Integer.valueOf(requestDTO.getDates().size())::equals)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Length of one or more of asset prices lists not same as length of dates list");
        }

        List<TimeSeriesResponseDTO> result = service.calculateAssetTimeSeries(requestDTO.getTimeSeriesPrices(), requestDTO.getDates());
        return ResponseEntity.ok().body(result);
    }

}
