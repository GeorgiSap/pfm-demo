package com.example.pfmdemo.service;

import com.example.pfmdemo.service.dto.TimeSeriesResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioManagementService {

    public static final double INITIAL_INVESTMENT = 1000;

    public List<TimeSeriesResponseDTO> calculateAssetTimeSeries(BigDecimal[][] prices, List<LocalDate> dates) {
        double weight = 1d / prices.length;
        List<TimeSeriesResponseDTO> response = new ArrayList<>(dates.size());
        response.add(new TimeSeriesResponseDTO(dates.get(0), new BigDecimal(INITIAL_INVESTMENT)));

        for (int col = 1; col < prices[0].length; col++) {
            double returnRate = 1;
            for (int row = 0; row < prices.length; row++) {
                returnRate += (prices[row][col].doubleValue() - prices[row][0].doubleValue())
                        / prices[row][0].doubleValue() * weight;
            }
            response.add(new TimeSeriesResponseDTO(dates.get(col), new BigDecimal(INITIAL_INVESTMENT * returnRate)));
        }
        return response;
    }
}
