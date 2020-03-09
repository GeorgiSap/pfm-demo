package com.example.pfmdemo.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimeSeriesResponseDTO {
    LocalDate date;
    BigDecimal value;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
