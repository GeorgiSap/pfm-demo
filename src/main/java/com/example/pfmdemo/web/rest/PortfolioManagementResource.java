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

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PortfolioManagementResource {

    public static final String LENGHT_MISMATCH_ERROR_MESSAGE = "Length of one or more of asset prices lists not same as length of dates list";

    private final Logger log = LoggerFactory.getLogger(PortfolioManagementResource.class);

    private final PortfolioManagementService service;

    public PortfolioManagementResource(PortfolioManagementService service) {
        this.service = service;
    }

    @GetMapping("portfolio-managements/time-series")
    public ResponseEntity<List<TimeSeriesResponseDTO>> calculateAssetTimeSeries(@Valid @RequestBody TimeSeriesRequestDTO dto) {
        log.debug("REST request to calculate time series of a portfolio : {}, {}", dto.getPrices(), dto.getDates());
        boolean isDataLengthValid = Arrays.stream(dto.getPrices())
                .map(price -> price.length)
                .allMatch(Integer.valueOf(dto.getDates().size())::equals);
        if (!isDataLengthValid) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LENGHT_MISMATCH_ERROR_MESSAGE);
        }
        List<TimeSeriesResponseDTO> result = service.calculateAssetTimeSeries(dto.getPrices(), dto.getDates());
        return ResponseEntity.ok().body(result);
    }

}
