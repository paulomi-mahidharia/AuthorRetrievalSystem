package com.neu.msd.DBLPXMLParser.config;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
	private static Properties prop = new Properties();
	private static String dbUrl;

	private static String user;
	private static String password;

	static {
		try {
			prop.load(DBConnection.class.getClassLoader().getResourceAsStream("application.properties"));
			dbUrl = prop.getProperty("aws.db.connection.url");
			user = prop.getProperty("aws.db.username");
			password = prop.getProperty("aws.db.password");

		} catch (Exception e) {
			dbUrl = "jdbc:mysql://127.0.0.1:3306/dblp";
			user = "root";
			password = "team3";
			System.out.println("Unable to read Properties file");
		}
	}

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbUrl, user, password);
		} catch (Exception e) {
			System.out.println("Error while opening a conneciton to database server: " + e.getMessage());
			return null;
		}
	}
}
