package com.ikramdg.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

	private static final String TABLE_INITIALIZER_SQL = "CREATE TABLE IF NOT EXISTS t_employee ("
			+ "employee_id		INT," + "first_name		VARCHAR(50)," + "last_name		VARCHAR(50),"
			+ "department_id	INT," + "PRIMARY KEY(employee_id) " + ");" + "CREATE TABLE IF NOT EXISTS t_department ("
			+ "department_id	INT," + "department_name	VARCHAR(50)," + "PRIMARY KEY(department_id)" + ");";

	private static final String ADD_CONSTRAINTS = "ALTER TABLE t_employee ADD CONSTRAINT employee_department_fkey FOREIGN KEY (department_id) REFERENCES t_department(department_id);";

	public static void init(Connection connection) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute(TABLE_INITIALIZER_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Cannot initialized");
			System.exit(0);
		}
		try {
			statement.execute(ADD_CONSTRAINTS);
		} catch (Exception e) {
		}
	}

}
