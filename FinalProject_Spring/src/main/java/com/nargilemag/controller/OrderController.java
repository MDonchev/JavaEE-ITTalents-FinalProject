package com.nargilemag.controller;

import static org.mockito.Mockito.doThrow;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nargilemag.model.Order;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;
import com.nargilemag.model.dao.OrderDao;
import com.nargilemag.model.dao.ProductDao;
import com.nargilemag.util.exceptions.NotEnoughMoneyForOrderException;
import com.nargilemag.util.exceptions.OrderedProductsAmmountException;

@Controller
public class OrderController {

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String showProductsInCart(HttpServletRequest request) {
		
		Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		
		request.setAttribute("cart", cart);
		
		return "order";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public synchronized String finalizeOrder(HttpServletRequest request) {
		
		HashMap<Product, Integer> cart = (HashMap<Product, Integer>) request.getSession().getAttribute("cart");
		double totalPrice = 0;
		
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			for(Product product : cart.keySet()) {
				if(product.getAmmountInStock() < cart.get(product)) {
					throw new OrderedProductsAmmountException("Not enough in stock");
				}
				totalPrice += cart.get(product) * product.getPrice();
			}
			
			if(user.getBalance() < totalPrice) {
				throw new NotEnoughMoneyForOrderException("Not enough money in balance, " + (totalPrice - user.getBalance()) + " short");
			}
			
			
			Order order = new Order(LocalDate.now(), user.getAddress(), user.getPhoneNumber(), user.getId(), cart);
			for(Product product : cart.keySet()) {
				
				OrderDao.INSTANCE.addOrderFromUser(order, user);
				OrderDao.INSTANCE.addProductToOrder(order, product);
				ProductDao.INSTANCE.updateProductAmmountInStock(product.getId(), product.getAmmountInStock() - cart.get(product));
				
			}
			user.setBalance(user.getBalance() - totalPrice);
			
			
			
		}
		catch (OrderedProductsAmmountException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		catch (NotEnoughMoneyForOrderException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
		
		return "redirect:/";
	}
	
}
