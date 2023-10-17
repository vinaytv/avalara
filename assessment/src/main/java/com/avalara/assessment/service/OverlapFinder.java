package com.avalara.assessment.service;

import com.avalara.assessment.dto.DateOverlapResponse;
import com.avalara.assessment.dto.DateRange;
import com.avalara.assessment.dto.DateRangeRequest;
import com.avalara.assessment.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OverlapFinder {


    public DateOverlapResponse checkOverLap(DateRangeRequest request) {
        if (null == request.getRange1() || null == request.getRange1().getStart() || null == request.getRange1().getEnd() || null == request.getRange2()
                || null == request.getRange2().getStart() || null == request.getRange2().getEnd()) {

            throw new BadRequestException("Date range with start and end is mandatory.");
        }
        DateRange range1 = request.getRange1();
        DateRange range2 = request.getRange2();

        boolean overlap = overlapsWith(range1, range2);
        DateOverlapResponse dateOverlapResponse = new DateOverlapResponse();
        dateOverlapResponse.setOverlappingPeriod(getOverlapPeriod(range1, range2));
        dateOverlapResponse.setOverlap(overlap);
        return dateOverlapResponse;
    }


    public boolean overlapsWith(DateRange range1, DateRange range2) {
        return range1.getStart().before(range2.getEnd()) && range2.getStart().before(range1.getEnd());
    }


    public DateRange getOverlapPeriod(DateRange otherRange1, DateRange otherRange2) {
        if (overlapsWith(otherRange1, otherRange2)) {
            Date overlapStart = otherRange1.getStart().after(otherRange2.getStart()) ? otherRange1.getStart() : otherRange2.getStart();
            Date overlapEnd = otherRange1.getEnd().before(otherRange2.getEnd()) ? otherRange1.getEnd() : otherRange2.getEnd();
            return new DateRange(overlapStart, overlapEnd);
        } else {
            return null;
        }
    }
}
