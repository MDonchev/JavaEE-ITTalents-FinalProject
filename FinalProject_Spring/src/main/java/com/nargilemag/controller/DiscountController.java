package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.ProductDao;
import com.nargilemag.util.exceptions.ProductDataException;
import com.nargilemag.util.validation.ProductCredentialValidation;

@Controller
public class DiscountController {

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	
	@RequestMapping(value = "/discount", method = RequestMethod.GET)
	public String showDiscountPage(Model model, HttpSession session, HttpServletRequest request) {
		
		
		try {
			
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
		Product product = new Product();
		
		model.addAttribute("products", allProducts);
		model.addAttribute("product", product);
		
		return "/productDiscount";
	}
	
	@RequestMapping(value = "/discount", method = RequestMethod.POST)
	public String applyDiscount(@ModelAttribute Product product, Model model, HttpServletRequest request, HttpSession session) {
		
		try {
			
			ProductCredentialValidation.numberValidation(product.getDiscountPercent());
			ProductDao.INSTANCE.updateDiscountPercentByProductId(product.getId(), product.getDiscountPercent());
			List<String> usersFavEmails = ProductDao.INSTANCE.getAllEmailsOfUsersWithFavoriteProductId(product.getId());
			
			List<Product> products = ProductDao.INSTANCE.getAllProducts();
			model.addAttribute("loggedUser", session.getAttribute("user"));
			model.addAttribute("products", products);
			User u = (User)session.getAttribute("user");
			List<Product> userFavouritesProducts = ProductDao.INSTANCE.getUserFavourites(u);
			model.addAttribute("favourites", userFavouritesProducts);
			

			new Thread(() -> { 
					MailSender.INSTANCE.sendEmail(mailSender,usersFavEmails,"Your favourite product: "+ product.getName() +" has been updated");
				}).start(); 
				
		} catch (SQLException | ProductDataException e) {
			request.setAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
		
		
		
		return "index";
	}
	
}
