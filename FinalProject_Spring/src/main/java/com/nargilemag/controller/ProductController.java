package com.nargilemag.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nargilemag.model.Category;
import com.nargilemag.model.Characteristic;
import com.nargilemag.model.Gender;
import com.nargilemag.model.Product;
import com.nargilemag.model.dao.CategoryDao;
import com.nargilemag.model.dao.CharacteristicDao;
import com.nargilemag.model.dao.ProductDao;
import com.nargilemag.model.dao.UserDao;

@Controller
public class ProductController {

	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProductPage(HttpServletRequest request) {
		try {
			//get genders from db
			List<Category> categories = CategoryDao.INSTANCE.getSubCategories();
			List<Characteristic> characteristics = CharacteristicDao.INSTANCE.getAllCharacteristics();
			//add them to request
			request.setAttribute("categories", categories);
			request.setAttribute("character", characteristics);
			//forward this request to register.jsp
			return "addproduct";
		} catch (SQLException e) {
			return "error";
		}
	}
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request) {
		try {
			String name = request.getParameter("name");
			String desc = request.getParameter("desc");
			Double price = Double.parseDouble(request.getParameter("price"));
			Integer ammount = Integer.parseInt(request.getParameter("ammount"));
			Integer category = Integer.parseInt(request.getParameter("category"));
			Integer ch_value = Integer.parseInt(request.getParameter("ch_ammo"));
			
			List<Characteristic> characteristics = new ArrayList<>();
			Integer character = Integer.parseInt(request.getParameter("characteristics"));
			String ch_name = CharacteristicDao.INSTANCE.getCharacteristicNameByID(character);
			characteristics.add(new Characteristic(character,ch_name, ch_value));
//			System.out.println(name);
//			System.out.println(desc);
//			System.out.println(price);
//			System.out.println(ammount);
//			System.out.println(category);
//			System.out.println(characteristics.size());
			Product p = new Product(name, desc, price, ammount, category, characteristics);
			
			ProductDao.INSTANCE.saveProduct(p);
			
			
			
			return "redirect:/";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/updateProductAmount", method = RequestMethod.GET)
	public String updateProductsPage(HttpServletRequest request) {
		return "updateProduct";
	}
	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public String updateProductPage(HttpServletRequest request) {

		int productId = Integer.parseInt(request.getParameter("changed_product"));
		
		Product p = null;
		try {
			p = ProductDao.INSTANCE.getProductBtID(productId);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
		request.setAttribute("product", p);
		
		return "updateProductAmount";
	}
	@RequestMapping(value = "/updateProductAmount", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute Product product, HttpServletRequest request) {
		try {
			//update product with dao
			Integer ammount = Integer.parseInt(request.getParameter("ammount"));
			
			ProductDao.INSTANCE.updateProductAmmountInStock(product.getId(), product.getAmmountInStock() + ammount);
			
			return "redirect:/";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
	}
}
