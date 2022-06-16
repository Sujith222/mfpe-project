package com.mfpe.memberService.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.memberService.exceptions.InvalidMemberIdException;
import com.cts.memberService.exceptions.InvalidTokenException;
import com.cts.memberService.model.Bills;
import com.cts.memberService.repository.BillsRepo;
import com.cts.memberService.service.ClaimStatusAndDetails;

@SpringBootTest
public class ClaimStatusAndDetailsTest {

	@MockBean
    private BillsRepo billsRepo;
	
	@InjectMocks
	ClaimStatusAndDetails claimStatusAndDetails;

    
    
	
    @Test
    @DisplayName("Checking if [ClaimStatusAndDetailsTest] class is loading or not.")
     void processingRequestIsLoadedOrNot() {
        assertThat(claimStatusAndDetails).isNotNull();
    }

    @DisplayName("Checking if Get Bills Method is working or not")
    @Test
    void testGetBillsMethod() {
    	Date d1 = Date.valueOf("2021-05-04");
    	Date d2 = Date.valueOf("2020-06-04");
    	
    	Bills bill = new Bills("221", d1,65600,1200,d2);
    	billsRepo.save(bill);
        assertThat(billsRepo.findById("221")).isNotNull();
    }
    
  
   
    
    
}
    