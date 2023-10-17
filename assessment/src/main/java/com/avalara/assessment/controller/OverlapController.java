package com.avalara.assessment.controller;

import com.avalara.assessment.dto.DateOverlapResponse;
import com.avalara.assessment.dto.DateRangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.avalara.assessment.service.OverlapFinder;

@RestController
public class OverlapController {

    @Autowired
    private OverlapFinder overlapFinder;

    /**
     * API to check if date range overlaps or not.
     * @param request
     * @return DateOverlapResponse
     */
    @PostMapping("/check-overlap")
    public ResponseEntity<DateOverlapResponse> checkOverlap(@RequestBody DateRangeRequest request) {
        return new ResponseEntity<>(overlapFinder.checkOverLap(request), HttpStatus.OK);
    }



}
