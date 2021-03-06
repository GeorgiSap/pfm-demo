package com.example.pfmdemo.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimeSeriesResponseDTO {
    LocalDate date;
    BigDecimal value;

    public TimeSeriesResponseDTO(LocalDate date, BigDecimal value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public TimeSeriesResponseDTO date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TimeSeriesResponseDTO value(BigDecimal value) {
        this.value = value;
        return this;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
