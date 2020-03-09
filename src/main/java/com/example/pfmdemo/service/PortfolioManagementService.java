package com.example.pfmdemo.service;

import com.example.pfmdemo.service.dto.TimeSeriesResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioManagementService {

    public List<TimeSeriesResponseDTO> calculateAssetTimeSeries(List<List<BigDecimal>> timeSeriesPrices, List<LocalDate> dates) {
        if (!isValid(timeSeriesPrices, dates)) {
            throw new IllegalStateException();
        }

        BigDecimal initialPortfolioInvestment = new BigDecimal(1000);

        List<BigDecimal> initialPrices = new ArrayList<>();
        for (List<BigDecimal> timeSeriesPrice : timeSeriesPrices) {
            initialPrices.add(timeSeriesPrice.get(0));
        }

        double weight = 1d / initialPrices.size();

        List<TimeSeriesResponseDTO> result = new ArrayList<>(dates.size());
        TimeSeriesResponseDTO timeSeriesResponseDTO = new TimeSeriesResponseDTO();
        timeSeriesResponseDTO.setDate(dates.get(0));
        timeSeriesResponseDTO.setValue(initialPortfolioInvestment);
        result.add(timeSeriesResponseDTO);

        for (int dateIndex = 1; dateIndex < dates.size(); dateIndex++) {
            List<BigDecimal> currentDatePrices = new ArrayList<>();

            double calc = 0;

            for (List<BigDecimal> timeSeriesPrice : timeSeriesPrices) {
                currentDatePrices.add(timeSeriesPrice.get(dateIndex));
            }

            for (int i = 0; i < timeSeriesPrices.size(); i++) {
                calc += (currentDatePrices.get(i).doubleValue() - initialPrices.get(i).doubleValue())/initialPrices.get(i).doubleValue() * weight;
            }

            double value = (1 + calc) * initialPortfolioInvestment.doubleValue();

            TimeSeriesResponseDTO timeSeriesResponseDTO1 = new TimeSeriesResponseDTO();
            timeSeriesResponseDTO1.setDate(dates.get(dateIndex));
            timeSeriesResponseDTO1.setValue(new BigDecimal(value));

            result.add(timeSeriesResponseDTO1);
        }

        return result;
    }

    private boolean isValid(List<List<BigDecimal>> timeSeriesPrices, List<LocalDate> dates) {
        return timeSeriesPrices.stream().map(List::size).allMatch(Integer.valueOf(dates.size())::equals);
    }
}
