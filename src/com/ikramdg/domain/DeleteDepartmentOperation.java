package com.ikramdg.domain;

import java.sql.SQLException;

import com.ikramdg.exceptions.DepartmentNotFoundException;
import com.ikramdg.exceptions.OperationExecutionException;

public class DeleteDepartmentOperation extends Operation {

	private static final String DELETE_DEPARTMENT_COMMAND = "deleteDepartment";

	public DeleteDepartmentOperation() {
		super(DELETE_DEPARTMENT_COMMAND, "Delete department");
	}

	@Override
	public Boolean execute() throws Exception {
		if (!ensureArguments()) {
			throw new OperationExecutionException();
		}
		getAllDepartmentsResultSet().beforeFirst();
		while(getAllDepartmentsResultSet().next()) {
			if(getAllDepartmentsResultSet().getInt("department_id") == getIntValueOfArgument(0)) {
				try {
					getAllDepartmentsResultSet().deleteRow();
				} catch (SQLException e) {
					System.err.println("There are registered employees in this department");
					e.printStackTrace();
					return false;
				}
				return true;
			}
		}
		throw new DepartmentNotFoundException();
	}

	@Override
	public boolean ensureArguments() {
		return getOperationArguments().size() == 1;
	}

}
