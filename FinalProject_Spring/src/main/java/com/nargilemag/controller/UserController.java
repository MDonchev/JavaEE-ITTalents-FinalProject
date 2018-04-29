package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nargilemag.model.Gender;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.UserDao;
import com.nargilemag.util.exceptions.UserDataException;
import com.nargilemag.util.validation.UserCredentialsValidation;



@Controller
public class UserController {

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(){
		return "login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request) throws SQLException {
		try {
			
			User u = UserDao.INSTANCE.getUserFromLogin(username, password);
			
			if(u != null) {
				request.getSession().setAttribute("user", u);
				request.getSession().setAttribute("cart", new HashMap<Product,Integer>());
				return "redirect:/";
			}
			else {
				throw new UserDataException("invalid username or password");
			}
		}
		catch (UserDataException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String userLogout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterPage(HttpServletRequest request) {
		try {
			//get genders from db
			List<Gender> genders = UserDao.INSTANCE.getAllGenders();
			//add them to request
			request.setAttribute("genders", genders);
			//forward this request to register.jsp
			return "register";
		} catch (SQLException e) {
			return "error";
		}
		
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUSer(HttpServletRequest request) {
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
			UserDao.INSTANCE.saveUser(u);
			request.getSession().setAttribute("user", u);
			request.getSession().setAttribute("cart", new HashMap<Product,Integer>());
			//TODO
			//getServletConfig().getServletContext().setAttribute("products", ProductDao.getInstance().getAll());
			
			//forward to login OR main
			return "redirect:/";
		}
		catch (UserDataException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		catch (Exception e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
	
	@RequestMapping(value= "/addToCart", method = RequestMethod.GET)
	public String showAddToCart(Model m, HttpServletRequest request) {
		
		int productId = Integer.parseInt(request.getParameter("ordered_product"));
		request.setAttribute("productID", productId);
		
		return "addtocart";
	}
	
	@RequestMapping(value= "/addToCart", method = RequestMethod.POST)
	public String addToCart(HttpServletRequest request) {
		Integer amount = Integer.parseInt((String)request.getParameter("amount"));
		HashMap<Product, Integer> cart = (HashMap<Product, Integer>)request.getSession().getAttribute("cart");
		cart.put((Product)request.getAttribute("product"), amount);
		request.getSession().setAttribute("cart", cart);
		return "redirect:/";
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
