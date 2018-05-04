package com.nargilemag.controller;

import static org.mockito.Mockito.doThrow;

import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
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
	public String showProductsInCart(HttpServletRequest request) {
		
		Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		
		request.setAttribute("cart", cart);
		
		return "order";
	}
	 
	
	@RequestMapping(value = "/decrease", method = RequestMethod.POST)
	public String decreaseCount(HttpServletRequest request) {
		Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		String productName = request.getParameter("productName");
		
		for(Product product : cart.keySet()) {
			boolean broke = false;
			if(product.getName().compareTo(productName) == 0) {
				if(cart.get(product) > 0) {
					cart.put(product, cart.get(product) - 1);
					broke = true;
				}
			}
			if(cart.get(product) <= 0) {
				cart.remove(product);
				if(broke == true) {
					break;
				}
			}
		}
		
		request.getSession().setAttribute("cart", cart);
		
		return "/order";
	}
	
	@RequestMapping(value = "/finalize", method = RequestMethod.POST)
	public synchronized String finalizeOrder(HttpServletRequest request) {
		
		HashMap<Product, Integer> cart = (HashMap<Product, Integer>) request.getSession().getAttribute("cart");
		double totalPrice = 0;
		
		User user = (User) request.getSession().getAttribute("user");
		
		Connection connection = DBManager.INSTANCE.getConnection();
		
		try {
			
			for(Product product : cart.keySet()) {
				if(product.getAmmountInStock() < cart.get(product)) {
					throw new OrderedProductsAmmountException("Not enough in stock");
				}
				totalPrice += cart.get(product) * (product.getPrice() - product.getDiscountPercent() / 100 * product.getPrice()); //subtracting the discount amount
			}
			
			if(user.getBalance() < totalPrice) {
				throw new NotEnoughMoneyForOrderException("Not enough money in balance, " + (totalPrice - user.getBalance()) + " short");
			}
			
			
			try {
				connection.setAutoCommit(false);
				Order order = new Order(LocalDate.now(), user.getAddress(), user.getPhoneNumber(), user.getId(), cart);
				for(Product product : cart.keySet()) {
					OrderDao.INSTANCE.addOrderFromUser(order, user);
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
