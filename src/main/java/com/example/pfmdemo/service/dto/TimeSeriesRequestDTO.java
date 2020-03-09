package com.example.pfmdemo.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TimeSeriesRequestDTO implements Serializable {
    List<List<BigDecimal>> timeSeriesPrices;
    List<LocalDate> dates;

    public List<List<BigDecimal>> getTimeSeriesPrices() {
        return timeSeriesPrices;
    }

    public void setTimeSeriesPrices(List<List<BigDecimal>> timeSeriesPrices) {
        this.timeSeriesPrices = timeSeriesPrices;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }
}
