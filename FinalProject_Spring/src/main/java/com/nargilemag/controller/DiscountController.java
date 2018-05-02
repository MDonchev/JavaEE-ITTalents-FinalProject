package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nargilemag.model.Product;
import com.nargilemag.model.dao.ProductDao;

@Controller
public class DiscountController {

	@RequestMapping(value = "/discount", method = RequestMethod.GET)
	public String showDiscountPage(Model model, HttpSession session, HttpServletRequest request) {
		
		List<Product> allProducts = new ArrayList<Product>();
		try {
			allProducts = ProductDao.INSTANCE.getAllProducts();
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
	public String applyDiscount(@ModelAttribute Product product, Model model, HttpServletRequest request) {
		
		
		try {
			ProductDao.INSTANCE.updateDiscountPercentByProductId(product.getId(), product.getDiscountPercent());
			
			
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
		
		
		
		return "index";
	}
	
}
