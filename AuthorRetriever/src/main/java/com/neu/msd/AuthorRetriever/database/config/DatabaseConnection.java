package com.neu.msd.AuthorRetriever.database.config;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * This class is use to set database connection with aws RDS or local db
 * This class is a singleton class and implements singletons design pattern
 *
 */
public class DatabaseConnection {
    	
	private static Properties prop = new Properties();
	private static String dbUrl;

	private static String user;
	private static String password;
	
	private static Connection connection;
		
	static{
		try {
			prop.load(DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties"));
			dbUrl = prop.getProperty("aws.db.connection.url");
			user = prop.getProperty("aws.db.username");
			password = prop.getProperty("aws.db.password");
			
		} catch (Exception e) {
			System.out.println("Unable to read Properties file");
			// Jenkins is unable to read from properties file.
			// provide aws creds
			dbUrl = "jdbc:mysql://dblp.c1lyqqia3dks.us-east-1.rds.amazonaws.com:3306/dblp_new";
			user = "msddblp";
			password = "zxcv1234";
			
		}
	}
	
	
	public static Connection getConn() {
		synchronized(DatabaseConnection.class){
			try {
				if (connection == null || connection.isClosed()) {
					connectToDB();
				}
			} catch (SQLException e) {
				System.out.println("Error while connecting to database server: "+ e.getMessage());
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	private static void connectToDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, user, password);
		} catch (Exception e) {
			System.out.println("Error while opening a conneciton to database server: "
								+ e.getMessage());
			return;
		}
	}
} 
