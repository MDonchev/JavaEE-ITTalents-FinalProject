package com.nargilemag.util.validation;

import com.nargilemag.util.exceptions.ProductDataException;

public final class ProductCredentialValidation {
	
	private static final boolean stringValidation(String str) {
		return str != null && !str.trim().isEmpty();
	}
	
	public static void numberValidation(String number) throws ProductDataException{
		ProductDataException exception = new ProductDataException("number must be whole and possitive.");
		if (!stringValidation(number)) {
			throw exception;
		}
		if(number.length() == 1 && number.charAt(0) == '0') {
			throw exception;
		}
		for(int i = 0; i< number.length(); i++) {
			if(!('0' <= number.charAt(i) && number.charAt(i)<= '9')){
				throw exception;
			}
		}
	}
	
	public static void checkCredentials(String name, String desc, String price, String ammount, String category,
			String character, String ch_value, String extension) throws ProductDataException{
		stringValidation(name);
		stringValidation(desc);
		numberValidation(price);
		numberValidation(ammount);
		numberValidation(ch_value);
		
		if(!(extension.equals("jpg")) && !(extension.equals("jpeg"))) {
			throw new ProductDataException("Only jpg or jpeg.");
		}
	}

	public static void numberValidation(int discountPercent) throws ProductDataException{
		if(discountPercent < 0 || discountPercent > 100) throw new ProductDataException("Wrong discount");
	}

}
