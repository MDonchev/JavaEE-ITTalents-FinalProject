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

import com.nargilemag.model.Category;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.CategoryDao;
import com.nargilemag.model.dao.ProductDao;
import com.nargilemag.util.exceptions.ProductDataException;
import com.nargilemag.util.validation.ProductCredentialValidation;

@Controller
public class DiscountController {

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	
	@RequestMapping(value = "/discount", method = RequestMethod.GET)
	public String showDiscountPage(Model model, HttpSession session, HttpServletRequest request) {
		
		if (session.getAttribute("user") != null) {
			try {
				Integer discountProductId = Integer.parseInt(request.getParameter("dicount_prod_id"));
				
				Product product = ProductDao.INSTANCE.getProductBtID(discountProductId);
				request.setAttribute("product", product);
				
				return "/productDiscount";
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("exception", e);
				return "error";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/discount", method = RequestMethod.POST)
	public String applyDiscount(@ModelAttribute Product product, Model model, HttpServletRequest request, HttpSession session) {
		
		try {
			
			Integer discountPercent = Integer.parseInt(request.getParameter("ammount"));
			
			ProductCredentialValidation.numberValidation(discountPercent);
			ProductDao.INSTANCE.updateDiscountPercentByProductId(product.getId(), discountPercent);
			List<String> usersFavEmails = ProductDao.INSTANCE.getAllEmailsOfUsersWithFavoriteProductId(product.getId());
			
			List<Product> products = ProductDao.INSTANCE.getAllProducts();
			
			model.addAttribute("products", products);
			
			List<Product> promotions = ProductDao.INSTANCE.getAllPromotions();
			List<Category> mainCategories = CategoryDao.INSTANCE.getCategories();
			
			
			model.addAttribute("promotions", promotions);
			model.addAttribute("categories", mainCategories);
			
			

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
