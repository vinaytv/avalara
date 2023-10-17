package com.avalara.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class DateOverlapResponse {
    private boolean overlap;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DateRange overlappingPeriod;

    public boolean isOverlap() {
        return overlap;
    }

    public void setOverlap(boolean overlap) {
        this.overlap = overlap;
    }

    public DateRange getOverlappingPeriod() {
        return overlappingPeriod;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setOverlappingPeriod(DateRange overlappingPeriod) {
        this.overlappingPeriod = overlappingPeriod;
    }
}
