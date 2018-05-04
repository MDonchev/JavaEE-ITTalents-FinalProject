package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nargilemag.model.Gender;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.ProductDao;
import com.nargilemag.model.dao.UserDao;
import com.nargilemag.util.exceptions.UserDataException;
import com.nargilemag.util.validation.ProductCredentialValidation;
import com.nargilemag.util.validation.UserCredentialsValidation;



@Controller
public class UserController {

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(){
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request, HttpSession session) throws SQLException {
		try {
			
			User u = UserDao.INSTANCE.getUserFromLogin(username, password);
			
			if(u != null) {
				session.setAttribute("user", u);
				session.setAttribute("cart", new HashMap<Product,Integer>());
				return "redirect:/";
			}
			else {
				throw new UserDataException("invalid username or password");
			}
		}
		catch (UserDataException | SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String userLogout(HttpSession session){
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
	public String registerUSer(HttpServletRequest request, HttpSession session) {
		try {

			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("number");
			Integer gender = Integer.parseInt(request.getParameter("gender"));

			//validate data
			UserCredentialsValidation.validateData(username, password1, password2, email, address, phoneNumber);
			
			//create user
			User u = new User(username, password1, email, address, phoneNumber, 500, gender, false); //TODO: everyone starts with 500 cash by default
			//save user in data base and current session
			UserDao.INSTANCE.saveUser(u);
			
			session.setAttribute("user", u);
			session.setAttribute("cart", new HashMap<Product,Integer>());
			
			//forward to login OR main
			return "redirect:/";
		}
		catch (UserDataException | SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
	
	@RequestMapping(value= "/addToCart", method = RequestMethod.GET)
	public String showAddToCart(Model m, HttpServletRequest request) {
		
		try {
			int productId = Integer.parseInt(request.getParameter("ordered_product"));
			
			Product p = null;
			
			p = ProductDao.INSTANCE.getProductBtID(productId);
			
			request.setAttribute("product", p);
			
			return "addtocart";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
	}
	
	@RequestMapping(value= "/addToCart", method = RequestMethod.POST)
	public String addToCart(@ModelAttribute Product product, HttpServletRequest request, HttpSession session) {
		try {
			String ammo = request.getParameter("ammount");
			ProductCredentialValidation.numberValidation(ammo);
			Integer ammount = Integer.parseInt(ammo);
			HashMap<Product, Integer> cart = (HashMap<Product, Integer>)session.getAttribute("cart");
			
			if(!cart.containsKey(product)) {
				cart.put(product, 0);
			}
			cart.put(product, cart.get(product) + ammount);
			
			session.setAttribute("cart", cart);
			return "redirect:/";
		} catch (Exception e) {
			request.setAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value= "/addToFavourites", method = RequestMethod.POST)
	public String addToFavourites(HttpServletRequest request) {
		try {
			User u = (User)request.getSession().getAttribute("user");
			int productId = Integer.parseInt(request.getParameter("fav_product"));
			UserDao.INSTANCE.addToFavorites(u.getId(),productId);
			return "redirect:/";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
	@RequestMapping(value= "/removeFavourite", method = RequestMethod.POST)
	public String removeFromFavourites(HttpServletRequest request) {
		try {
			User u = (User)request.getSession().getAttribute("user");
			int productId = Integer.parseInt(request.getParameter("fav_product"));

			UserDao.INSTANCE.removeFromFavourites(u.getId(),productId);
			return "redirect:/";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
	
}
