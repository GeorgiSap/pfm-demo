package com.example.pfmdemo.service;

import com.example.pfmdemo.service.dto.TimeSeriesDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioManagementService {

    public static final double INITIAL_INVESTMENT = 1000;

    public List<TimeSeriesDTO> calculateAssetTimeSeries(BigDecimal[][] prices, List<LocalDate> dates) {
        List<TimeSeriesDTO> response = new ArrayList<>(dates.size());
        double weight = 1d / prices.length;

        response.add(new TimeSeriesDTO()
                .date(dates.get(0))
                .value(new BigDecimal(INITIAL_INVESTMENT)));

        for (int col = 1; col < prices[0].length; col++) {
            double returnRate = 1;
            for (int row = 0; row < prices.length; row++) {
                returnRate += (prices[row][col].doubleValue() - prices[row][0].doubleValue())
                        / prices[row][0].doubleValue() * weight;
            }

            TimeSeriesDTO timeSeriesDTO = new TimeSeriesDTO()
                    .date(dates.get(col))
                    .value(new BigDecimal(INITIAL_INVESTMENT * returnRate));

            response.add(timeSeriesDTO);
        }

        return response;
    }

}
