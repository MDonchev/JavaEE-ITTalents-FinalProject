package com.nargilemag.util.validation;

import com.nargilemag.util.exceptions.UserDataException;

public final class UserCredentialsValidation {

	private static final boolean stringValidation(String str) {
		return str != null && !str.trim().isEmpty();
	}
	
	private static final boolean mailValidation(String email) {
		return stringValidation(email) && email.matches("[\\w|.|-]*@\\w*\\.[\\w|.]+");
	}
	
	private static final boolean usernameValidation(String name) {
		return stringValidation(name) && checkName(name);
	}
	private static final boolean passwordValidation(String password) {
		//password:
		//at least 1 digit
		//at least 1 lower case char
		//at least 1 upper case char
		//at least 1 special symbol in @,#,$,%,^,&,+,=,-
		//no whitespaces
		//at least 7 symbols lenght
		return stringValidation(password) && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_])(?=\\S+$).{7,}$");
	}
	
	private static final boolean numberValidation(String number) {
		//number starts with 08 and have 10 symbols , only digits
		return stringValidation(number) && number.matches("08[0-9]{8}");
	}
	
	private static boolean checkName(String str) {
		//name starts with letter, otherwise -> false
		if (!Character.isLetter(str.charAt(0))) return false;
		
		//contains only letters and numbers
		for(int i = 0; i< str.length(); i++) {
			if(!Character.isLetter(str.charAt(i)) && !('0' <= str.charAt(i) && str.charAt(i)<= '9')){
				return false;
			}
		}
		return true;
	}
	
	public static void validateData (String name, String password1 ,String password2 ,String email, String address, String phoneNumber) throws UserDataException{
		if (!password1.equals(password2)) {
			throw new UserDataException("Password mismatch.");
		}
		if (!UserCredentialsValidation.usernameValidation(name)) {
			String errMessage = "Username must starts with letter and contains only letters or digits.";
			throw new UserDataException(errMessage);
		}
		if (!UserCredentialsValidation.passwordValidation(password1)) {
			String errMessage = "Password must be at least 7 symbols and contains at least: 1 small letter, 1 big letter, 1 digit, 1 special symbol and must be without whitespaces";
			throw new UserDataException(errMessage);
		}
		if(!UserCredentialsValidation.mailValidation(email)) {
			String errMessage = "Email must be valid.";
			throw new UserDataException(errMessage);
		}
		if(!UserCredentialsValidation.stringValidation(address)) {
			String errMessage = "Address must be valid.";
			throw new UserDataException(errMessage);
		}
		if(!UserCredentialsValidation.numberValidation(phoneNumber)) {
			String errMessage = "Phone number must be 10 digits and starts with 08";
			throw new UserDataException(errMessage);
		}
	}
}