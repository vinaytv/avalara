package com.avalara.assessment;

import com.avalara.assessment.dto.DateOverlapResponse;
import com.avalara.assessment.dto.DateRange;
import com.avalara.assessment.dto.DateRangeRequest;
import com.avalara.assessment.exception.BadRequestException;
import com.avalara.assessment.service.OverlapFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AssessmentApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	OverlapFinder overlapFinder;

	@Test
	void checkOverLapTestPositive() throws ParseException {
		DateRangeRequest dateRangeRequest= new DateRangeRequest();
		DateRange dateRange1=new DateRange(new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01"),new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-15"));
		DateRange dateRange2=new DateRange(new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-10"),new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-01"));
		dateRangeRequest.setRange1(dateRange1);
		dateRangeRequest.setRange2(dateRange2);
		DateOverlapResponse dateOverlapResponse=overlapFinder.checkOverLap(dateRangeRequest);
        assertTrue(dateOverlapResponse.isOverlap());
	}

	@Test
	void checkOverLapTestPositiveCotainsOverlappingPeriod() throws ParseException {
		DateRangeRequest dateRangeRequest= new DateRangeRequest();
		DateRange dateRange1=new DateRange(new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01"),new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-15"));
		DateRange dateRange2=new DateRange(new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-10"),new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-01"));
		dateRangeRequest.setRange1(dateRange1);
		dateRangeRequest.setRange2(dateRange2);
		DateOverlapResponse dateOverlapResponse=overlapFinder.checkOverLap(dateRangeRequest);
		assertTrue(dateOverlapResponse.isOverlap());
		assertNotNull(dateOverlapResponse.getOverlappingPeriod());
	}

	@Test
	void checkOverLapTestNegative() throws ParseException {
		DateRangeRequest dateRangeRequest= new DateRangeRequest();
		DateRange dateRange1=new DateRange(new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01"),new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-25"));
		DateRange dateRange2=new DateRange(new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-26"),new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-01"));
		dateRangeRequest.setRange1(dateRange1);
		dateRangeRequest.setRange2(dateRange2);
		DateOverlapResponse dateOverlapResponse=overlapFinder.checkOverLap(dateRangeRequest);
		Assertions.assertFalse(dateOverlapResponse.isOverlap());
	}


	@Test
	void checkOverLapTestException() throws ParseException {
		DateRangeRequest dateRangeRequest= new DateRangeRequest();
		DateRange dateRange1=new DateRange(null,null);
		DateRange dateRange2=new DateRange(new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-26"),new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-01"));
		dateRangeRequest.setRange1(dateRange1);
		dateRangeRequest.setRange2(dateRange2);
		BadRequestException badRequestException=assertThrows(
				BadRequestException.class,
				() -> overlapFinder.checkOverLap(dateRangeRequest),
				"Date range with start and end is mandatory."
		);
		assertTrue(badRequestException.getMessage().contains("mandatory"));
	}
}
