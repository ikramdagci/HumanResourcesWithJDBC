package com.ikramdg.v1.domain;

import com.ikramdg.v1.exceptions.EmployeeNotFoundException;
import com.ikramdg.v1.exceptions.OperationExecutionException;

public class UpdateEmployeNameOperation extends Operation{

	private static final String UPDATE_EMPLOYEE_NAME_COMMAND ="updateEmployeeName";
	
	public UpdateEmployeNameOperation() {
		super(UPDATE_EMPLOYEE_NAME_COMMAND, "Update employee's name");
	}

	@Override
	public Boolean execute() throws Exception {
		if(!ensureArguments()) {
			throw new OperationExecutionException("incorrect argument number");
		}
		getAllEmployeesResultSet().beforeFirst();
		while(getAllEmployeesResultSet().next()) {
			if(getAllEmployeesResultSet().getInt("employee_id") == getIntValueOfArgument(0)) {
				getAllEmployeesResultSet().updateString("first_name", getOperationArguments().get(1));
				getAllEmployeesResultSet().updateString("last_name", getOperationArguments().get(2));
				getAllEmployeesResultSet().updateRow();
				return true;
			}
		}
		throw new EmployeeNotFoundException();
	}

	@Override
	public boolean ensureArguments() {
		return getOperationArguments().size() == 3;
	}

}
