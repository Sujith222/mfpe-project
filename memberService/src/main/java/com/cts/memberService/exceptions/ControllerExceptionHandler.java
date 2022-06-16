package com.cts.memberService.exceptions;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cts.memberService.model.ErrorMessage;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice

public class ControllerExceptionHandler {
	public static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(value = InvalidClaimIdException.class)
	public ResponseEntity<ErrorMessage> invalidPolicyId(InvalidClaimIdException e, WebRequest request) {

		log.info("***Check Invalid PolicyId***");

		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);

	}

	@ExceptionHandler(value = InvalidMemberIdException.class)
	public ResponseEntity<ErrorMessage> invalidMemberId(InvalidMemberIdException e, WebRequest request) {

		log.info("***Check Invalid MemberId***");

		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);

	}

	@ExceptionHandler(value = InvalidPolicyIdException.class)
	public ResponseEntity<ErrorMessage> invalidPolicyId(InvalidPolicyIdException e, WebRequest request) {

		log.info("***Check Invalid MemberId***");

		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);

	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorMessage> feignExceptionHandling(FeignException e, WebRequest request) {

		log.info("***Check Invalid MemberId***");

		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(),
				"Token is either expired or invalid...", request.getDescription(false));

		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
}
