package com.nargilemag.util.exceptions;

public class NotEnoughMoneyForOrderException extends Exception {

	private String message;
	
	public NotEnoughMoneyForOrderException(String message) {
		super(message);
	}
}
