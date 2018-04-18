package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.ProductDao;
import model.dao.UserDao;
import util.BCrypt;
import util.exceptions.UserDataException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			User u = UserDao.getInstance().getUserFromLogin(username, password);
			
			if(u != null) {
				request.getSession().setAttribute("user", u);
				//getServletConfig().getServletContext().setAttribute("products", ProductDao.getInstance().getAll());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else {
				throw new UserDataException("invalid username or password");
			}
		}
		catch (UserDataException e) {
			System.out.println("userdata");
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}
}