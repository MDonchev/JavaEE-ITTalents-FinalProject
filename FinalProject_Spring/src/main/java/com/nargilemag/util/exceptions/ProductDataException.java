package com.nargilemag.util.exceptions;

public class ProductDataException extends Exception{

	private String wrongValue;
	
	public ProductDataException(String wrongVal) {
		super("Invalid product credentials:  " + wrongVal);
	}
}
