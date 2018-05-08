package com.nargilemag.controller;


import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nargilemag.model.Order;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.DBManager;
import com.nargilemag.model.dao.OrderDao;
import com.nargilemag.model.dao.ProductDao;
import com.nargilemag.model.dao.UserDao;
import com.nargilemag.util.exceptions.NotEnoughMoneyForOrderException;
import com.nargilemag.util.exceptions.OrderedProductsAmmountException;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@RequestMapping(value = {"","decrease"}, method = RequestMethod.GET)
	public String showProductsInCart(Model model, HttpSession session) {
		
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		
		Double totalPrice = 0.0;
		for(Product product : cart.keySet()) {
			totalPrice += cart.get(product) * product.getPrice();
		}
		
		model.addAttribute("product", new Product());
		session.setAttribute("totalPrice", totalPrice);
		
		
		return "order";
	}
	 
	
	@RequestMapping(value = "/decrease", method = RequestMethod.POST)
	public String decreaseCount(@ModelAttribute Product product, HttpSession session, Model model) {
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		Double totalPrice = (Double)session.getAttribute("totalPrice");
		
		if(cart.containsKey(product)) {
			if(cart.get(product) > 1) {
				cart.put(product, cart.get(product) - 1);
			}
			else {
				cart.remove(product);
			}
			totalPrice -= product.getPrice();
		}
		
		
		session.setAttribute("cart", cart);
		session.setAttribute("totalPrice", totalPrice);
		
		return "/order";
	}
	
	@RequestMapping(value = "/increase", method = RequestMethod.POST)
	public String increaseCount(@ModelAttribute Product product, HttpSession session) {
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		Double totalPrice = (Double)session.getAttribute("totalPrice");
		
		if(cart.containsKey(product)) {
			cart.put(product, cart.get(product) + 1);
			totalPrice += product.getPrice();
		}
		
		session.setAttribute("cart", cart);
		session.setAttribute("totalPrice", totalPrice);
		
		return "/order";
	}
	
	@RequestMapping(value = "/finalize", method = RequestMethod.POST)
	public synchronized String finalizeOrder(HttpServletRequest request) {
		
		HashMap<Product, Integer> cart = (HashMap<Product, Integer>) request.getSession().getAttribute("cart");
		double totalPrice = (Double)request.getSession().getAttribute("totalPrice");
		
		User user = (User) request.getSession().getAttribute("user");
		
		Connection connection = DBManager.INSTANCE.getConnection();
		
		try {
			
			for(Product product : cart.keySet()) {
				if(product.getAmmountInStock() < cart.get(product)) {
					throw new OrderedProductsAmmountException("Not enough in stock");
				}
			}
			
			if(user.getBalance() < totalPrice) {
				throw new NotEnoughMoneyForOrderException("Not enough money in balance, " + (totalPrice - user.getBalance()) + " short");
			}
			
			
			try {
				connection.setAutoCommit(false);
				Order order = new Order(LocalDate.now(), user.getAddress(), user.getPhoneNumber(), user.getId(), cart);
				OrderDao.INSTANCE.addOrderFromUser(order, user);
				for(Product product : cart.keySet()) {
					OrderDao.INSTANCE.addProductToOrder(order, product);
					ProductDao.INSTANCE.updateProductAmmountInStock(product.getId(), product.getAmmountInStock() - cart.get(product));
					
				}
				
				UserDao.INSTANCE.updateBalanceById(user.getId(), user.getBalance() - totalPrice);
				user.setBalance(user.getBalance() - totalPrice);
				
				request.getSession().setAttribute("cart", new HashMap<>());
				connection.commit();
			}
			catch (SQLException e) {
				connection.rollback();
				throw new SQLException("order transaction failed");
			}
			finally {
				connection.setAutoCommit(true);
			}
			
			
		}
		catch (OrderedProductsAmmountException | NotEnoughMoneyForOrderException | SQLException e) {
			request.setAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
		
		
		return "redirect:/";
	}
	
}
