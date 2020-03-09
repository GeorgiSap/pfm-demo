package com.example.pfmdemo.service;

import com.example.pfmdemo.service.dto.TimeSeriesResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PortfolioManagementService {

    public static final double INITIAL_INVESTMENT = 1000;

    public List<TimeSeriesResponseDTO> calculateAssetTimeSeries(List<List<BigDecimal>> timeSeriesPrices, List<LocalDate> dates) {
        List<BigDecimal> initialPrices = timeSeriesPrices.stream()
                .map(prices -> prices.get(0))
                .collect(Collectors.toList());

        double weight = 1d / initialPrices.size();

        return  IntStream.range(0, dates.size())
                .mapToObj(index -> calculateSingleTimeSeries(timeSeriesPrices, dates, initialPrices, weight, index))
                .collect(Collectors.toList());
    }

    private TimeSeriesResponseDTO calculateSingleTimeSeries(List<List<BigDecimal>> timeSeriesPrices, List<LocalDate> dates, List<BigDecimal> initialPrices, double weight, int dateIndex) {
        if (dateIndex == 0) {
            return new TimeSeriesResponseDTO()
                .date(dates.get(0))
                .value(new BigDecimal(INITIAL_INVESTMENT));
        }

        List<BigDecimal> currentDatePrices = timeSeriesPrices.stream().map(t -> t.get(dateIndex)).collect(Collectors.toList());
        double returnRate = calculateReturnRate(timeSeriesPrices, initialPrices, weight, currentDatePrices);

        return new TimeSeriesResponseDTO()
            .date(dates.get(dateIndex))
            .value(new BigDecimal(returnRate * INITIAL_INVESTMENT));
    }

    private double calculateReturnRate(List<List<BigDecimal>> timeSeriesPrices, List<BigDecimal> initialPrices, double weight, List<BigDecimal> currentDatePrices) {
        double returnRate = 1;
        for (int i = 0; i < timeSeriesPrices.size(); i++) {
            returnRate += (currentDatePrices.get(i).doubleValue() - initialPrices.get(i).doubleValue())/initialPrices.get(i).doubleValue() * weight;
        }

        return returnRate;
    }

}
