package com.nargilemag.util.exceptions;

public class OrderedProductsAmmountException extends Exception {

	private String message;
	
	public OrderedProductsAmmountException(String message) {
		super(message);
	}
}
