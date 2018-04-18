package util.validation;

public final class UserCredentialsValidation {

	public static final boolean stringValidation(String str) {
		return str != null && !str.trim().isEmpty();
	}
	
	public static final boolean mailValidation(String email) {
		return stringValidation(email) && email.matches("[\\w|.|-]*@\\w*\\.[\\w|.]+");
	}
	
	public static final boolean usernameValidation(String name) {
		return stringValidation(name) && checkName(name);
	}
	public static final boolean passwordValidation(String password) {
		//password:
		//at least 1 digit
		//at least 1 lower case char
		//at least 1 upper case char
		//at least 1 special symbol in @,#,$,%,^,&,+,=,-
		//no whitespaces
		//at least 7 symbols lenght
		return stringValidation(password) && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_])(?=\\S+$).{7,}$");
	}
	
	public static final boolean numberValidation(String number) {
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
}
