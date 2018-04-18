package model.dao;

import java.sql.SQLException;

import model.User;
import util.exceptions.UserDataException;

public interface IUserDao {

	User getUserFromLogin(String username, String password) throws SQLException, UserDataException;
	User getUserById(int userId) throws SQLException;
	void saveUser(User u) throws SQLException;
}
