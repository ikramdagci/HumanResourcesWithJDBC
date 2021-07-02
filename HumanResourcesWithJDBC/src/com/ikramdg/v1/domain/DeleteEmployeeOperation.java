package com.ikramdg.v1.domain;

import com.ikramdg.exceptions.EmployeeNotFoundException;

public class DeleteEmployeeOperation extends Operation {

	public DeleteEmployeeOperation() {
		super("deleteEmployee","Deletes an employee");
	}

	@Override
	public Boolean execute() throws Exception {
		getAllEmployeesResultSet().beforeFirst();
		while (getAllEmployeesResultSet().next()) {
			if (getAllEmployeesResultSet().getInt("employee_id") == Integer.parseInt(getOperationArguments().get(0))) {
				getAllEmployeesResultSet().deleteRow();
				return true;
			}
		}
		throw new EmployeeNotFoundException();
	}

	@Override
	public boolean ensureArguments() {
		return false;
	}

}
