package com.ikramdg.domain;

import java.sql.SQLException;

import com.ikramdg.exceptions.OperationExecutionException;

public class DisplayDepartmentsOperation extends Operation{

	private static final String DISPLAY_ALL_DEPARTMENTS_FORMAT = "%15d  | %-15s\n";
	private static final String DISPLAY_ALL_DEPARTMENTS_HEADER_FORMAT = "%15s  | %-15s\n----------------------------------\n";
	
	
	public DisplayDepartmentsOperation() {
		super("displayDepartments","Display all departments");
	}

	@Override
	public Void execute() throws OperationExecutionException, SQLException {
		if(!ensureArguments()) {
			throw new OperationExecutionException();
		}
		System.out.printf(DISPLAY_ALL_DEPARTMENTS_HEADER_FORMAT,"department_id","department_name");
		getAllDepartmentsResultSet().beforeFirst();
		while (getAllDepartmentsResultSet().next()) {
			System.out.printf(DISPLAY_ALL_DEPARTMENTS_FORMAT, 
					getAllDepartmentsResultSet().getInt("department_id"),
					getAllDepartmentsResultSet().getString("department_name"));
		}
		return null;
	}

	@Override
	public boolean ensureArguments() {
		return getOperationArguments().size() == 0;
	}

}
