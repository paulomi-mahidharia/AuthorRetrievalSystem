package com.neu.msd.AuthorRetriever.database.config;
import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    	// Change the parameters accordingly.
		//private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/dblp?useUnicode=true&characterEncoding=utf-8";
		private static String dbUrl = "jdbc:mysql://dblp.c1lyqqia3dks.us-east-1.rds.amazonaws.com:3306/dblp";

		private static String user = "msddblp";
		private static String password = "zxcv1234";

		public static Connection getConn() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				return DriverManager.getConnection(dbUrl, user, password);
			} catch (Exception e) {
				System.out.println("Error while opening a conneciton to database server: "
									+ e.getMessage());
				return null;
			}
		}
} 
