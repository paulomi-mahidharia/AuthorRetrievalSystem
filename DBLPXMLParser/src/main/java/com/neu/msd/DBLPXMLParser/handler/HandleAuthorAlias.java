package com.neu.msd.DBLPXMLParser.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.DBLPXMLParser.config.DBConnection;
import com.neu.msd.DBLPXMLParser.model.AuthorAlias;

public class HandleAuthorAlias {

	private Connection conn;
	PreparedStatement stmt_author_details;
	
	public HandleAuthorAlias() throws SQLException{
		conn = DBConnection.getConn();
		conn.setAutoCommit(false);
		stmt_author_details = conn
				.prepareStatement("insert into author_alias(alias_name, author_name) "
						+ "values (?,?)");
		}

	public boolean insertAuthorAlias(List<AuthorAlias> authorAliases) throws SQLException{
		
		for(AuthorAlias a: authorAliases){
			
			String author=null;
			if(!a.getAuthors().isEmpty()){
				author = a.getAuthors().get(0);
			}
			for(String auth: a.getAuthors()){
				stmt_author_details.setString(1, auth);
				stmt_author_details.setString(2, author);
				stmt_author_details.addBatch();
			}
			
		}
		stmt_author_details.executeBatch();
		conn.commit();
		return true;
	}
}
