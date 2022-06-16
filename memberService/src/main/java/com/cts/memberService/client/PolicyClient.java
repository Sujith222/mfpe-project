package com.cts.memberService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "policy-service", url = "${Policy.URL}")
public interface PolicyClient {
	@GetMapping(path = "/isPolicyValid/{memberId}/{policyId}")
	public Boolean verifyPolicy(@PathVariable(value = "memberId") String memberId,@PathVariable(value = "policyId") String policyId,
			@RequestHeader(name = "Authorization", required = true) String token);

}
