package com.ikramdg.v1.domain;

import com.ikramdg.v1.exceptions.DepartmentNotFoundException;
import com.ikramdg.v1.exceptions.OperationExecutionException;

public class UpdateDepartmentNameOperation extends Operation {

	private static final String UPDATE_DEPARTMENT_NAME_COMMAND = "updateDepartmentName";

	public UpdateDepartmentNameOperation() {
		super(UPDATE_DEPARTMENT_NAME_COMMAND, "Update specified department's name");
	}

	@Override
	public Boolean execute() throws Exception {
		if (!ensureArguments()) {
			throw new OperationExecutionException();
		}
		getAllDepartmentsResultSet().beforeFirst();
		while (getAllDepartmentsResultSet().next()) {
			if (getAllDepartmentsResultSet().getInt("department_id") == getIntValueOfArgument(0)) {
				getAllDepartmentsResultSet().updateString("department_name", getOperationArguments().get(1));
				getAllDepartmentsResultSet().updateRow();
				return true;
			}
		}
		throw new DepartmentNotFoundException();
	}

	@Override
	public boolean ensureArguments() {
		return getOperationArguments().size() == 2;
	}

}
