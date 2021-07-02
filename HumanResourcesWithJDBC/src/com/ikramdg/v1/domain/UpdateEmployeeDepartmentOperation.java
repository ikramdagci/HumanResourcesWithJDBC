package com.ikramdg.v1.domain;

import com.ikramdg.v1.exceptions.DepartmentNotFoundException;
import com.ikramdg.v1.exceptions.EmployeeNotFoundException;
import com.ikramdg.v1.exceptions.OperationExecutionException;

public class UpdateEmployeeDepartmentOperation extends Operation {

	private static final String UPDATE_EMPLOYEE_DEPARTMENT_COMMAND = "updateEmployeeDepartment";

	public UpdateEmployeeDepartmentOperation() {
		super(UPDATE_EMPLOYEE_DEPARTMENT_COMMAND, "Update employee's department");
	}

	@Override
	public Boolean execute() throws Exception {
		if(!ensureArguments()) {
			throw new OperationExecutionException("incorrect arguments");
		}
		if(!ensureDepartmentExist(Integer.valueOf(getOperationArguments().get(1)))) {
			throw new DepartmentNotFoundException("Department with ID : " + getOperationArguments().get(1) + " not found" );
		}
		
		getAllEmployeesResultSet().beforeFirst();
		while(getAllEmployeesResultSet().next()) {
			if(getAllEmployeesResultSet().getInt("employee_id") == Integer.valueOf(getOperationArguments().get(0))) {
				getAllEmployeesResultSet().updateInt("department_id", Integer.valueOf(getOperationArguments().get(1)));
				getAllEmployeesResultSet().updateRow();
				return true;
			}
		}
		throw new EmployeeNotFoundException("Employee with ID: " + getOperationArguments().get(0) + " not found");
	}

	@Override
	public boolean ensureArguments() {
		return getOperationArguments().size() == 2;
	}

}
