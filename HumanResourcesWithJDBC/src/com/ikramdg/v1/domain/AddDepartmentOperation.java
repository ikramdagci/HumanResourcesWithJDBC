package com.ikramdg.v1.domain;

import com.ikramdg.v1.exceptions.OperationExecutionException;

public class AddDepartmentOperation extends Operation {

	public AddDepartmentOperation() {
		super("addDepartment","Adds a department");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean execute() throws Exception {
		if (!ensureArguments()) {
			throw new OperationExecutionException();
		}
		int id = -1;
		try {
			getAllDepartmentsResultSet().last();
			id = getAllDepartmentsResultSet().getInt("department_id") + 1;
		} catch (Exception e) {
			id = 1;
		}finally {
			getAllDepartmentsResultSet().moveToInsertRow();
		}
		try {
			getAllDepartmentsResultSet().updateInt("department_id", id);
			getAllDepartmentsResultSet().updateString("department_name", getOperationArguments().get(0));
			getAllDepartmentsResultSet().insertRow();
		} finally {
			getAllDepartmentsResultSet().moveToCurrentRow();
		}
		return true;
	}

	@Override
	public boolean ensureArguments() {
		return getOperationArguments().size() == 1;
	}

}
