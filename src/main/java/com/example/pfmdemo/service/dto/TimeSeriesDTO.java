package com.example.pfmdemo.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimeSeriesDTO {
    LocalDate date;
    BigDecimal value;

    public LocalDate getDate() {
        return date;
    }

    public TimeSeriesDTO date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TimeSeriesDTO value(BigDecimal value) {
        this.value = value;
        return this;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
