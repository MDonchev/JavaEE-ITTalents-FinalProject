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
import util.exceptions.UserDataException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
			//TODO the rest as well
			User u = UserDao.getInstance().getUser(username, pass);
			if(u != null) {
				request.getSession().setAttribute("user", u);
				getServletConfig().getServletContext().setAttribute("products", ProductDao.getInstance().getAll());
				request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
			}
			else {
				throw new UserDataException("invalid username or password");
			}
		}
		catch (UserDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}