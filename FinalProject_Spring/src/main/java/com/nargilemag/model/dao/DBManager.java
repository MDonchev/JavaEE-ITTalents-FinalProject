package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBManager {
	
	INSTANCE;
	
	private static final String DB_PASS = "root";
	private static final String DB_USER = "root";
	private static final String DB_PORT = "3306";
	private static final String DB_IP = "localhost";
	private static final String DB_NAME = "nargilemag";
	
//	private static final String DB_PASS = "marcoreus11";
//	private static final String DB_USER = "root";
//	private static final String DB_PORT = "3306";
//	private static final String DB_IP = "localhost";
//	private static final String DB_NAME = "nargilemag";
	
	private Connection connection;
	
	
	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, Driver not loaded or does not exist! Aborting.");
			return;
		}
		//create connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://"+DB_IP+":"+DB_PORT+"/" + DB_NAME, DB_USER, DB_PASS);
			System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}