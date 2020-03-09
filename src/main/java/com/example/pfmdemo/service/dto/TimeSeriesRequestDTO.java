package com.example.pfmdemo.service.dto;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TimeSeriesRequestDTO {

    @NotEmpty
    BigDecimal[][] prices;

    @NotEmpty
    List<LocalDate> dates;

    public BigDecimal[][] getPrices() {
        return prices;
    }

    public void setPrices(BigDecimal[][] prices) {
        this.prices = prices;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }
}
