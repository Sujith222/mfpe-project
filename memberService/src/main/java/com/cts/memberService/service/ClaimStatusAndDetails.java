package com.cts.memberService.service;


import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.memberService.client.ClaimsClient;
import com.cts.memberService.dto.BillsDto;
import com.cts.memberService.dto.ClaimStatusDTO;
import com.cts.memberService.exceptions.InvalidClaimIdException;
import com.cts.memberService.exceptions.InvalidMemberIdException;
import com.cts.memberService.model.Bills;
import com.cts.memberService.model.ClaimDetails;
import com.cts.memberService.repository.BillsRepo;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class ClaimStatusAndDetails{
	public static final Logger log=LoggerFactory.getLogger(ClaimStatusAndDetails .class);
        @Autowired
        private BillsRepo billsRepo;
        
        @Autowired
        private ClaimsClient claimsClient;
        
        // This method fetch claim Status from Claims MicroService
        public  ClaimStatusDTO fetchClaimStatus(String claimId, String token)
        {
            log.info("***fetchClaimStatus Method***");
            ClaimStatusDTO claimStatusDTO  = new ClaimStatusDTO();
            
            log.info("Claims Client Called");
            try {
            claimStatusDTO = claimsClient.statusDetails(claimId,token);
            }
            catch(Exception e)
            {
                throw new InvalidClaimIdException("The Claim Id is Invalid");
            }
            
            return claimStatusDTO;
        }
        
        
        // This method fetch Bills from Bills table
        public  ResponseEntity<BillsDto> fetchBills(String memberId)
        {
            log.info("Inside the fetch Bills method : Begin");
            BillsDto billsDto=new BillsDto();
            Bills bills=billsRepo.getById(memberId);
            
            billsDto.setDueDate(bills.getDueDate());
            billsDto.setDueAmount(bills.getDueAmount());
            billsDto.setLastPaidDate(bills.getLastPaidDate());
            billsDto.setLateCharge(bills.getLateCharge());
            
            Optional<Bills> bill = billsRepo.findById(memberId);
            
            if(!bill.isPresent() )
    		{
    			throw new InvalidMemberIdException("The Member Id is Invalid");
    		}
            
            return new ResponseEntity<BillsDto>(billsDto,HttpStatus.OK);
           
        }
        
        
        // This method submit the claim details to Claims MicroService
        public  ClaimStatusDTO fetchClaimDetails( ClaimDetails claimDetails,String token)
        {
            log.info("***fetchClaimDetails method***");
            ClaimStatusDTO claimStatusDTO  = new ClaimStatusDTO();
            claimDetails.setClaimId(generateClaimId());
            try {
            claimStatusDTO = claimsClient.onSbmitStatusDetails(claimDetails,token);
            }
            catch(Exception e)
            {
            	throw e;
            }
            return claimStatusDTO;
        }
        
        public String generateClaimId()
        {
        	return UUID.randomUUID().toString();
        }
        
}
