package com.ikramdg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	public static Connection connect() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBCredentials.DB_URL, DBCredentials.DB_USERNAME,
					DBCredentials.DB_PASSWORD);	
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

}
