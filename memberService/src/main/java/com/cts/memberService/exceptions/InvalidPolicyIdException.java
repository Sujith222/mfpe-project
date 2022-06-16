package com.cts.memberService.exceptions;

public class InvalidPolicyIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidPolicyIdException(String message)
	{
		super(message);
	}
}
