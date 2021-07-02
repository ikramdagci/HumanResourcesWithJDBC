package com.ikramdg.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DBInitializer {

	private static final String TABLE_INITIALIZER_SQL = "CREATE TABLE IF NOT EXIST t_employee ("
			+ "employee_id		INT," + "first_name		VARCHAR(50)," + "last_name		VARCHAR(50),"
			+ "department_id	INT," + "PRIMARY KEY(employee_id) " + ");" + "CREATE TABLE IF NOT EXIST t_department ("
			+ "department_id	INT," + "department_name	VARCHAR(50)," + "PRIMARY KEY(department_id)" + ");"
			+ "ALTER TABLE t_employee ADD CONSTRAINT employee_department_fkey FOREIGN KEY (department_id) REFERENCES t_department(department_id);";

	public static void init(Connection connection) {
		try {
			connection.createStatement().execute(TABLE_INITIALIZER_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
