package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Gender;
import model.User;
import model.dao.ProductDao;
import model.dao.UserDao;
import util.exceptions.UserDataException;
import util.validation.UserCredentialsValidation;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("number");
			Integer gender = Integer.parseInt(request.getParameter("gender"));

			//validate data
			validateData(username, password1, password2, email, address, phoneNumber);
			
			//create user
			User u = new User(username, password1, email, address, phoneNumber, gender, false);
			//save user in data base and current session
			UserDao.getInstance().saveUser(u);
			request.getSession().setAttribute("user", u);
			//TODO
			//getServletConfig().getServletContext().setAttribute("products", ProductDao.getInstance().getAll());
			
			//forward to login OR main
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		catch (UserDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//get genders from db
			List<Gender> genders = UserDao.getInstance().getAllGenders();
			//add them to request
			request.setAttribute("genders", genders);
			//forward this request to register.jsp
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
	}
	
	private void validateData (String name, String password1 ,String password2 ,String email, String address, String phoneNumber) throws UserDataException{
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