package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			return "error";
		}
	
	}
}
