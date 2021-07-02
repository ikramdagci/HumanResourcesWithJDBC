package com.ikramdg.v1.domain;

import java.sql.SQLException;

import com.ikramdg.v1.exceptions.OperationExecutionException;

public class DisplayAllEmployeesOperation extends Operation {

	private static final String DISPLAY_ALL_EMPLOYEES_FORMAT = "%11d  | %-15s | %-15s | %d\n";
	private static final String DISPLAY_ALL_EMPLOYEES_HEADER_FORMAT = "%-11s  | %-15s | %-15s | %s\n----------------------------------------------------------------\n";


	public DisplayAllEmployeesOperation() {
		super("displayEmployees","Display all employees");
	}

	@Override
	public Void execute() throws SQLException, OperationExecutionException {
		if(!ensureArguments()) {
			throw new OperationExecutionException();
		}
		System.out.printf(DISPLAY_ALL_EMPLOYEES_HEADER_FORMAT,"employee_id","first_name","last_name","department_id");
		getAllEmployeesResultSet().beforeFirst();
		while (getAllEmployeesResultSet().next()) {
			System.out.printf(DISPLAY_ALL_EMPLOYEES_FORMAT, 
					getAllEmployeesResultSet().getInt("employee_id"),
					getAllEmployeesResultSet().getString("first_name"),
					getAllEmployeesResultSet().getString("last_name"),
					getAllEmployeesResultSet().getInt("department_id"));
		}
		return null;
	}

	@Override
	public boolean ensureArguments() {
		return getOperationArguments().size() == 0;
	}

}
