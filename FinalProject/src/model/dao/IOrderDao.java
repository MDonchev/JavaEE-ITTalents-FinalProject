package model.dao;

import java.sql.SQLException;

import model.Order;
import model.User;

public interface IOrderDao {

	public void addOrderFromUser(Order order, User user) throws SQLException;
	
}
