package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.ProductDao;

@Controller
public class SearchBoxController {

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchByName(@RequestParam String name, Model model, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		model.addAttribute("loggedUser", u);
		
		ArrayList<Product> result = new ArrayList<>();
		try {
			result = ProductDao.INSTANCE.getProductsWithNameLike(name);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
		model.addAttribute("searchResult", result);
		
		
		
		
		
		return "searchResult";
	}
	
	
}
