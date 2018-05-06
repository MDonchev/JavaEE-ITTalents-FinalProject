package com.nargilemag.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.nargilemag.util.exceptions.ProductDataException;
import com.nargilemag.util.validation.ProductCredentialValidation;

@Controller
@MultipartConfig
public class ProductController {
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	private static final String PRODUCT_IMG_FILE_PATH = "/home/mario/Документи/uploads-FP-ITT/"; 
	
	
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProductPage(HttpServletRequest request) {
		try {
			//get needed information from db
			List<Category> categories = CategoryDao.INSTANCE.getCategories();
			List<Category> subcategories = CategoryDao.INSTANCE.getSubCategories();
			List<Characteristic> characteristics = CharacteristicDao.INSTANCE.getAllCharacteristics();
			//add them to request
			request.setAttribute("categories", categories);
			request.setAttribute("subcategories", subcategories);
			request.setAttribute("character", characteristics);
			//forward this request to addproduct.jsp
			return "addproduct";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			return "error";
		}
	}
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, @RequestParam("name") String name,
							@RequestParam("desc") String desc,
							@RequestParam("price") String price,
							@RequestParam("ammount") String ammount,
							@RequestParam("category") String category,
							@RequestParam("characteristics") String character,
							@RequestParam("ch_ammo") String ch_value,
							@RequestParam("fail") MultipartFile uploadedFile) {
		try {
			String extension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
			ProductCredentialValidation.checkCredentials(name,desc,price,ammount,category,character,ch_value,extension);
			
			Double price1 = Double.parseDouble(price);
			Integer ammount1 = Integer.parseInt(ammount);
			Integer category1 = Integer.parseInt(category);
			Integer ch_value1 = Integer.parseInt(ch_value);
			
			List<Characteristic> characteristics = new ArrayList<>();
			Integer character1 = Integer.parseInt(character);
			String ch_name = CharacteristicDao.INSTANCE.getCharacteristicNameByID(character1);
			Integer ch_category = CharacteristicDao.INSTANCE.getCharacteristicCategory(character1);
			characteristics.add(new Characteristic(character1,ch_name, ch_value1,ch_category));
			
			String fileName = uploadedFile.getOriginalFilename();
			File serverFile = new File(PRODUCT_IMG_FILE_PATH + fileName);
			Files.copy(uploadedFile.getInputStream(), serverFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			
			Product p = new Product(name, desc, price1, ammount1, category1, characteristics, fileName, 0); //initial discount percent is always 0
			
			ProductDao.INSTANCE.saveProduct(p);
				
			return "redirect:/";
		} catch (SQLException | IOException | ProductDataException e) {
			request.setAttribute("exception", e);
			e.printStackTrace();
			return "error";
		}
		
	}
	
	@RequestMapping(value="/download/{filename:.+}", method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse resp, @PathVariable("filename") String fileName, HttpServletRequest request) {
		File serverFile = new File(PRODUCT_IMG_FILE_PATH + fileName);
		try {
			Files.copy(serverFile.toPath(), resp.getOutputStream());
		} catch (IOException e) {
			request.setAttribute("exception", e);
		}
	}
	
	@RequestMapping(value = "/updateProductAmount", method = RequestMethod.GET)
	public String updateProductsPage(HttpServletRequest request, Model m) {
		try {
			List<Product> products = ProductDao.INSTANCE.getAllProducts();
			m.addAttribute("products", products);
			return "updateProduct";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
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
			String number = request.getParameter("ammount");
			
			ProductCredentialValidation.numberValidation(number);
			
			Integer ammount = Integer.parseInt(number);
			
			ProductDao.INSTANCE.updateProductAmmountInStock(product.getId(), product.getAmmountInStock() + ammount);
			List<String> usersFavEmails = ProductDao.INSTANCE.getAllEmailsOfUsersWithFavoriteProductId(product.getId());
			
			new Thread(() -> { 
					MailSender.INSTANCE.sendEmail(mailSender,usersFavEmails,"Your favourite product: "+ product.getName() +" has been updated");
				}).start(); 
				
			
			return "redirect:/";
		} catch (ProductDataException | SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
		
	}
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProductsPage(HttpServletRequest request, Model m) {
		try {
			List<Product> products = ProductDao.INSTANCE.getAllProducts();
			m.addAttribute("products", products);
			return "deleteProduct";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(HttpServletRequest request) {
		try {
			int productId = Integer.parseInt(request.getParameter("deleted_product"));
			String productName = request.getParameter("deleted_product_name");
			List<String> usersFavEmails = ProductDao.INSTANCE.getAllEmailsOfUsersWithFavoriteProductId(productId);
			
			new Thread(() -> { 
				MailSender.INSTANCE.sendEmail(mailSender, usersFavEmails, "Your favourite product: " + productName + " has been deleted");
			}).start();
			
			ProductDao.INSTANCE.deleteProductByID(productId);
			
			return "redirect:/";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			return "error";
		}
	}
}
