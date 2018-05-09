package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nargilemag.model.Category;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.CategoryDao;
import com.nargilemag.model.dao.ProductDao;

@Controller
public class PageController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage(Model m, HttpSession session){
		try{
			List<Product> products = ProductDao.INSTANCE.getAllProducts();
			List<Product> promotions = ProductDao.INSTANCE.getAllPromotions();
			List<Category> mainCategories = CategoryDao.INSTANCE.getCategories();
			
			m.addAttribute("products", products);
			m.addAttribute("promotions", promotions);
			session.setAttribute("categories", mainCategories);
			return "index";
		}
		catch (SQLException e) {
			m.addAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
	
	}
	@RequestMapping(value = "/category/{categoryID}", method = RequestMethod.GET)
	public String categoryPage(Model m, HttpSession session, @PathVariable("categoryID") Integer categoryID){
		try{
			List<Product> products = null;
			List<Category> subcategories = new ArrayList<>();
			Category cat = CategoryDao.INSTANCE.getCategoryById(categoryID);
			
			if(cat.getParent() == 0) {
				products = ProductDao.INSTANCE.getAllProductsByMainCategoryId(cat.getId());
				subcategories = CategoryDao.INSTANCE.getSubCategoriesByID(cat.getId());
			} else {
				products = ProductDao.INSTANCE.getAllProductsByCategoryId(cat.getId());
			}
	
			m.addAttribute("products", products);
			m.addAttribute("subcategories", subcategories);
			m.addAttribute("currentcat", cat);
			return "category";
		}
		catch (SQLException e) {
			m.addAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
	
	}
	@RequestMapping(value = "/sorted", method = RequestMethod.GET)
	public String showSortedProducts(Model model, HttpServletRequest request, HttpSession session) {
		
		List<Product> products = new ArrayList<>();
		try {
			products = ProductDao.INSTANCE.getAllProducts();
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
		
		TreeSet<Product> sortedProducts = new TreeSet<>((p1, p2) -> Double.compare(p1.getPrice() , p2.getPrice()));
		sortedProducts.addAll(products);
		
		model.addAttribute("products", sortedProducts);
		
		return "index";
	}
	@RequestMapping(value="/favourites", method=RequestMethod.GET)
	public String showFavourites(Model model, HttpServletRequest request, HttpSession session) {
		
		if (session.getAttribute("user") != null) {
			try {
				User u = (User)session.getAttribute("user");
				List<Product> favourites = new ArrayList<>();
				favourites = ProductDao.INSTANCE.getUserFavourites(u);

				model.addAttribute("favourites", favourites);
				
				return "favourites";
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				e.printStackTrace();
				return "error";
			}
		} else {
			return "redirect:/";
		}
	}
}
