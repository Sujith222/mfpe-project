package com.mfpe.memberService.controller;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;

import com.cts.memberService.client.AuthClient;
import com.cts.memberService.controller.MemberController;
import com.cts.memberService.dto.ClaimStatusDTO;
import com.cts.memberService.dto.ValidatingDTO;
import com.cts.memberService.exceptions.InvalidClaimIdException;
import com.cts.memberService.model.ClaimDetails;
import com.cts.memberService.service.ClaimStatusAndDetails;

@SpringBootTest
public class MemberControllerTest {

	@InjectMocks
	MemberController memberController;

	@Mock
	ClaimStatusAndDetails claimStatusAndDetails;

	@Mock
	AuthClient authClient;

	@Test
	@DisplayName("Checking for MemberController - if it is loading or not for @GetMapping")
	void MemberControllerNotNullTest() {

		MemberController memberController = new MemberController();
		assertThat(memberController).isNotNull();
	}

	@Test
	@DisplayName("Testing get claim Status is working correctly or not")
	public void testgetClaimStatus() {

		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(claimStatusAndDetails.fetchClaimStatus("AAAA", "token"))
				.thenReturn(new ClaimStatusDTO("AAAA", "Pending", "Need More Action"));

		ResponseEntity<ClaimStatusDTO> response = memberController.returnClaimStatus("AAAA", "token");

		assertEquals("Pending", response.getBody().getClaimStatus());
	}

	@Test
	@DisplayName("Testing get Claim Status invalid Claim Id Exception")
	public void testgetClaimStatus_fails1() {

		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(claimStatusAndDetails.fetchClaimStatus(anyString(), anyString())).thenThrow(InvalidClaimIdException.class);

		Assertions.assertThrows(InvalidClaimIdException.class, () -> {
			memberController.returnClaimStatus("AAAA", "token");
		});
	}

	@Test
	@DisplayName("Testing get Submitting Claim is working correctly or not")
	public void testgetClaimStatusOnUpdate() {

		ClaimDetails claimDetails = new ClaimDetails();
		claimDetails.setClaimId("AAAA");
		claimDetails.setStatus("Pending");
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(claimStatusAndDetails.fetchClaimDetails(claimDetails, "token"))
				.thenReturn(new ClaimStatusDTO("AAAA", "Pending", "Need More Action"));

		ResponseEntity<ClaimStatusDTO> response = memberController.returnClaimStatusOnUpdate(claimDetails, "token");

		assertEquals("Pending", response.getBody().getClaimStatus());
	}

}