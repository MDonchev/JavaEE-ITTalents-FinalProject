package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nargilemag.model.Category;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.CategoryDao;
import com.nargilemag.model.dao.ProductDao;

@Controller
public class SearchBoxController {

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchByName(@RequestParam String name, Model model, HttpServletRequest request) {
		try {
			ArrayList<Product> result = new ArrayList<>();
			result = ProductDao.INSTANCE.getProductsWithNameLike(name);
			
			model.addAttribute("searchResult", result);
			return "searchResult";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
	
	
}
