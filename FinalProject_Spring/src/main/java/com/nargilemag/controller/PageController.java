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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.ProductDao;

@Controller
public class PageController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage(Model m, HttpSession session){
		try{
			List<Product> products = ProductDao.INSTANCE.getAllProducts();
			List<Product> userFavouritesProducts = new ArrayList<Product>();
			User u = (User)session.getAttribute("user");
			if (u != null){
				userFavouritesProducts = ProductDao.INSTANCE.getUserFavourites(u);
			}
			m.addAttribute("loggedUser", u);
			m.addAttribute("products", products);
			m.addAttribute("favourites", userFavouritesProducts);
		
			return "index";
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
		List<Product> favourites = new ArrayList<>();
		try {
			products = ProductDao.INSTANCE.getAllProducts();
			favourites = ProductDao.INSTANCE.getUserFavourites((User)session.getAttribute("user"));
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
		
		TreeSet<Product> sortedProducts = new TreeSet<>((p1, p2) -> Double.compare(p1.getPrice() , p2.getPrice()));
		sortedProducts.addAll(products);
		
		model.addAttribute("loggedUser", session.getAttribute("user"));
		model.addAttribute("favourites", favourites);
		
		
		model.addAttribute("products", sortedProducts);
		
		return "index";
	}
	
}
