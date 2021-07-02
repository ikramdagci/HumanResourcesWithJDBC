package com.ikramdg.v1.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ikramdg.v1.util.DBConnector;
import com.ikramdg.v1.exceptions.IllegalArgumentException;
import com.ikramdg.v1.exceptions.OperationExecutionException;

public abstract class Operation {

	private String command;
	private String describe;
	private List<String> operationArguments;

	private static Connection connection;
	private static ResultSet allEmployeesResultSet;
	private static ResultSet allDepartmentsResultSet;

	private final static String GET_ALL_DEPARTMENTS_SQL = "SELECT * FROM t_department ORDER BY department_id ASC";
	private final static String GET_ALL_EMPLOYEES_SQL = "SELECT * FROM t_employee ORDER BY employee_id ASC";
	static {
		try {
			connection = DBConnector.connect();
			allEmployeesResultSet = connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery(GET_ALL_EMPLOYEES_SQL);

			allDepartmentsResultSet = connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery(GET_ALL_DEPARTMENTS_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Operation(String command, String description) {
		this.command = command;
		describe = description;
	}

	public static ResultSet getAllDepartmentsResultSet() {
		return allDepartmentsResultSet;
	}

	public static ResultSet getAllEmployeesResultSet() {
		return allEmployeesResultSet;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setOperationArguments(List<String> operationArguments) {
		this.operationArguments = operationArguments;
	}

	public List<String> getOperationArguments() {
		return operationArguments;
	}
	
	protected int getIntValueOfArgument(int index) throws IllegalArgumentException {
		if(index >= operationArguments.size()){
			throw new IllegalArgumentException();
		}
		return Integer.valueOf(operationArguments.get(index));
	}

	protected boolean ensureDepartmentExist(int departmentId) throws SQLException {
		getAllDepartmentsResultSet().beforeFirst();
		while (getAllDepartmentsResultSet().next()) {
			if (getAllDepartmentsResultSet().getInt("department_id") == departmentId)
				return true;
		}
		return false;
	}

	public abstract <T> T execute() throws Exception;

	public abstract boolean ensureArguments();

}
