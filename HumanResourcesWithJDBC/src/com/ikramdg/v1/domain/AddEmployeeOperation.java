package com.ikramdg.v1.domain;

import java.sql.SQLException;

import com.ikramdg.v1.exceptions.DepartmentNotFoundException;
import com.ikramdg.v1.exceptions.OperationExecutionException;

public class AddEmployeeOperation extends Operation {

	private static final String ADD_EMPLOYEE_TAG = "addEmployee";

	public AddEmployeeOperation() {
		super(ADD_EMPLOYEE_TAG, "Adds an employee");
	}

	@Override
	public Boolean execute() throws OperationExecutionException, SQLException {
		if (!ensureArguments()) {
			throw new OperationExecutionException();
		}
		if (!ensureDepartmentExist()) {
			throw new DepartmentNotFoundException();
		}
		int id = -1;
		try {
			getAllEmployeesResultSet().last();
			id = getAllEmployeesResultSet().getInt("employee_id") + 1;
		} catch (Exception e) {
			id = 1;
		} finally {
			getAllEmployeesResultSet().moveToInsertRow();
		}
		try {
			getAllEmployeesResultSet().updateInt("employee_id", id);
			getAllEmployeesResultSet().updateString("first_name", getOperationArguments().get(0));
			getAllEmployeesResultSet().updateString("last_name", getOperationArguments().get(1));
			getAllEmployeesResultSet().updateInt("department_id", Integer.parseInt(getOperationArguments().get(2)));
			getAllEmployeesResultSet().insertRow();
		} finally {
			getAllEmployeesResultSet().moveToCurrentRow();
		}
		return true;
	}

	@Override
	public boolean ensureArguments() {
		return getOperationArguments().size() == 3;
	}

	public boolean ensureDepartmentExist() throws SQLException {
		int departmentId = Integer.parseInt(getOperationArguments().get(2));
		getAllDepartmentsResultSet().beforeFirst();
		while (getAllDepartmentsResultSet().next()) {
			if (getAllDepartmentsResultSet().getInt("department_id") == departmentId)
				return true;
		}
		return false;
	}

}
