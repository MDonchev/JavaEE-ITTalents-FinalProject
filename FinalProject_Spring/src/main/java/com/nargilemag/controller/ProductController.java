package com.nargilemag.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nargilemag.model.Category;
import com.nargilemag.model.Characteristic;
import com.nargilemag.model.Gender;
import com.nargilemag.model.Product;
import com.nargilemag.model.dao.CategoryDao;
import com.nargilemag.model.dao.CharacteristicDao;
import com.nargilemag.model.dao.ProductDao;
import com.nargilemag.model.dao.UserDao;

@Controller
@MultipartConfig
public class ProductController {

	private static final String PRODUCT_IMG_FILE_PATH = "/home/mario/Документи/uploads-FP-ITT/"; 
	
	
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
	public String addProduct(HttpServletRequest request,@RequestParam("fail") MultipartFile uploadedFile) {
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
			
			String fileName = uploadedFile.getOriginalFilename();
			File serverFile = new File(PRODUCT_IMG_FILE_PATH + fileName);
			Files.copy(uploadedFile.getInputStream(), serverFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			
			Product p = new Product(name, desc, price, ammount, category, characteristics, fileName, 0); //initial discount percent is always 0
			
			ProductDao.INSTANCE.saveProduct(p);
				
			return "redirect:/";
		} catch (SQLException | IOException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
	}
	
	@RequestMapping(value="/download/{filename:.+}", method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse resp, @PathVariable("filename") String fileName, HttpServletRequest request) {
		System.out.println(fileName);
		File serverFile = new File(PRODUCT_IMG_FILE_PATH + fileName);
		try {
			Files.copy(serverFile.toPath(), resp.getOutputStream());
		} catch (IOException e) {
			request.setAttribute("exception", e);
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
