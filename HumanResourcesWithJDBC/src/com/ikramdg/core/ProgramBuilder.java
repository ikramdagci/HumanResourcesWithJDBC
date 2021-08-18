package com.ikramdg.core;

import java.util.ArrayList;
import java.util.List;

import com.ikramdg.domain.AddDepartmentOperation;
import com.ikramdg.domain.DeleteEmployeeOperation;
import com.ikramdg.domain.DisplayAllEmployeesOperation;
import com.ikramdg.domain.Operation;
import com.ikramdg.domain.AddEmployeeOperation;
import com.ikramdg.domain.DeleteDepartmentOperation;
import com.ikramdg.domain.DisplayDepartmentsOperation;
import com.ikramdg.domain.UpdateDepartmentNameOperation;
import com.ikramdg.domain.UpdateEmployeNameOperation;
import com.ikramdg.domain.UpdateEmployeeDepartmentOperation;

public class ProgramBuilder {

	public static Program build(String[] args) {
		Program program = new Program();
		Operation[] operations = { new AddEmployeeOperation(), new AddDepartmentOperation(),
				new DeleteEmployeeOperation(), new DisplayAllEmployeesOperation(), new DisplayDepartmentsOperation(),
				new UpdateDepartmentNameOperation(), new UpdateEmployeNameOperation(),new DeleteDepartmentOperation(), new UpdateEmployeeDepartmentOperation()};
		registerOperations(program, operations);
		program.setProgramArguments(extractArguments(args));
		return program;
	}

	private static ProgramArguments extractArguments(String[] args) {
		ProgramArguments arguments = new ProgramArguments();
		arguments.setOperationArgument(args[0]);
		arguments.setInfoArguments(getInfoArguments(args));
		return arguments;
	}

	private static void registerOperations(Program program, Operation[] operations) {
		for (Operation operation : operations) {
			program.registerOperation(operation);
		}
	}

	private static List<String> getInfoArguments(String[] args) {
		List<String> infoArguments = new ArrayList<String>();
		for (int i = 1; i < args.length; i++) {
			infoArguments.add(args[i]);
		}
		return infoArguments;
	}

}
