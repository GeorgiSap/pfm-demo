package com.example.pfmdemo.web.rest;

import com.example.pfmdemo.service.PortfolioManagementService;
import com.example.pfmdemo.service.dto.TimeSeriesRequestDTO;
import com.example.pfmdemo.service.dto.TimeSeriesResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PortfolioManagementResource {

    public static final String LENGHT_MISMATCH_ERROR_MESSAGE = "Length of one or more of asset prices lists not same as length of dates list";

    private final Logger log = LoggerFactory.getLogger(PortfolioManagementResource.class);

    private final PortfolioManagementService service;

    public PortfolioManagementResource(PortfolioManagementService service) {
        this.service = service;
    }

    @PostMapping("portfolio-managements/time-series")
    public ResponseEntity<List<TimeSeriesResponseDTO>> calculateAssetTimeSeries(@Valid @RequestBody TimeSeriesRequestDTO dto) {
        log.debug("REST request to calculate time series of a portfolio : {}, {}", dto.getPrices(), dto.getDates());
        boolean isDataLengthValid = Arrays.stream(dto.getPrices())
                .map(price -> price.length)
                .allMatch(Integer.valueOf(dto.getDates().size())::equals);
        if (!isDataLengthValid) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LENGHT_MISMATCH_ERROR_MESSAGE);
        }
        List<TimeSeriesResponseDTO> result = service.calculateAssetTimeSeries(dto.getPrices(), dto.getDates()).stream()
                .map(entry -> new TimeSeriesResponseDTO(entry.getDate(), entry.getValue().setScale(2, RoundingMode.HALF_UP)))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

}
