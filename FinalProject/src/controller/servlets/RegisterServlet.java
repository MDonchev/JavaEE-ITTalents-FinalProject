package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.ProductDao;
import model.dao.UserDao;
import util.exceptions.UserDataException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//validate data
			String username = request.getParameter("username");
			String pass1 = request.getParameter("password1");
			String pass2 = request.getParameter("password2");
			String email = request.getParameter("email");
			Integer gender = Integer.parseInt(request.getParameter("gender"));
			if(username.isEmpty() || username.length() < 5) {
				throw new UserDataException("username must be at least 5 chars long");
			}
			if(!pass1.equals(pass2)) {
				throw new UserDataException("passwords missmatch");
			}
			//TODO the rest as well
			//create user
			User u = new User(username, pass1, email, gender);
			//add to db
			UserDao.getInstance().saveUser(u);
			request.getSession().setAttribute("user", u);
			getServletConfig().getServletContext().setAttribute("products", ProductDao.getInstance().getAll());
			//forward to login OR main
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
		}
		catch (UserDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}