package com.cts.memberService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.memberService.dto.ClaimStatusDTO;
import com.cts.memberService.model.ClaimDetails;


@FeignClient(name = "claim-service", url = "${Claims.URL}")
public interface ClaimsClient {

		@GetMapping("/getClaimStatus/{claimId}")
		ClaimStatusDTO statusDetails(@PathVariable(value = "claimId") String claimId,@RequestHeader(name = "Authorization", required = true)String token);
		
		@PostMapping("/submitClaim")
		ClaimStatusDTO onSbmitStatusDetails(@RequestBody ClaimDetails claimDetails,@RequestHeader(name = "Authorization", required = true)String token);
}
