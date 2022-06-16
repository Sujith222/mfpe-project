package com.cts.memberService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.memberService.client.AuthClient;
import com.cts.memberService.client.PolicyClient;
import com.cts.memberService.dto.BillsDto;
import com.cts.memberService.dto.ClaimStatusDTO;
import com.cts.memberService.exceptions.InvalidPolicyIdException;
import com.cts.memberService.exceptions.InvalidTokenException;
import com.cts.memberService.model.ClaimDetails;
import com.cts.memberService.service.ClaimStatusAndDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;



@RestController

@RequestMapping("/memberModule")
@Api(value = "Member module endpoint")
@CrossOrigin
public class MemberController { 
	public static final Logger log=LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private ClaimStatusAndDetails ClaimStatusAndDetails;
    
     @Autowired
     private AuthClient authClient;
     
     @Autowired
     private PolicyClient policyClient;
     
     private String msg = "Token is either expired or invalid...";
    
    @ApiOperation(value = "to get the Bills for the User")
    @GetMapping(value="/viewBills/{memberId}/{policyId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillsDto> getBills( @PathVariable("memberId") String memberId,@PathVariable("policyId") String policyId,
            @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException
    {
        log.info("***getBills***");

    	if (!authClient.getsValidity(token).isValidStatus()) {
			throw new InvalidTokenException(msg);
		}
    	
    	if(!policyClient.verifyPolicy(memberId, policyId, token)) {
    		throw new InvalidPolicyIdException("Policy Id Not Correct");
    	}
	
        return  ClaimStatusAndDetails.fetchBills(memberId);
    }
    
    
    @ApiOperation(value = "To get the claim Status For Given Id")
    @GetMapping(value="/getClaimStatus/{claimId}", produces = "application/json")
    public ResponseEntity<ClaimStatusDTO> returnClaimStatus(@PathVariable("claimId") String claimId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException
    {
        
        log.info("***getClaimStatus***");
     
        if (!authClient.getsValidity(token).isValidStatus())  {
            
            throw new InvalidTokenException(msg);
        }
        
        return new ResponseEntity<>( ClaimStatusAndDetails.fetchClaimStatus(claimId, token), HttpStatus.OK);          
    }
    
    @PostMapping(value="/submitClaim", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "To Submit the Claim", response = ClaimStatusDTO.class, httpMethod = "POST")
    public ResponseEntity<ClaimStatusDTO> returnClaimStatusOnUpdate(@RequestBody ClaimDetails claimDetails,  @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException
    {
        log.info("***submitClaim***");
        
        if (!authClient.getsValidity(token).isValidStatus())  {
            
            throw new InvalidTokenException(msg);
        }
       
        return new ResponseEntity<>( ClaimStatusAndDetails.fetchClaimDetails(claimDetails,token), HttpStatus.OK);

    }
    
    
     
}
