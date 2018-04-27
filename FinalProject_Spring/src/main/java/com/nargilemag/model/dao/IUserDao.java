package com.nargilemag.model.dao;

import java.sql.SQLException;

import com.nargilemag.model.User;
import com.nargilemag.util.exceptions.UserDataException;

public interface IUserDao {

	User getUserFromLogin(String username, String password) throws SQLException, UserDataException;
	//User getUserById(int userId) throws SQLException;
	void saveUser(User u) throws SQLException;
}