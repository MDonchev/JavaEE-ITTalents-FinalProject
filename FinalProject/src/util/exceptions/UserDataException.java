package util.exceptions;

public class UserDataException extends Exception{
	
	private String wrongValue;
	
	public UserDataException(String wrongVal) {
		super("Invalid user credentials:  " + wrongVal);
	}

}