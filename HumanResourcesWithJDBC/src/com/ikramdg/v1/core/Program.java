package com.ikramdg.v1.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ikramdg.v1.domain.Operation;
import com.ikramdg.v1.exceptions.IllegalArgumentException;
import com.ikramdg.v1.exceptions.NoSuchOperationException;

public class Program {

	private static final String DEFAULT_OPERATION_DELIMITER = "-";
	private ProgramArguments arguments;
	private Map<String, Operation> operations;

	public Program() {
		this(new HashMap<String, Operation>());
	}

	public Program(Map<String, Operation> operations) {
		this.operations = operations;
	}

	public void run() throws Exception {
		if (!isOperationSyntaxValid(arguments.getOperationArgument())) {
			throw new IllegalArgumentException();
		}
		Operation operation = operations.get(getTagFromOperationArgument(arguments.getOperationArgument()));
		operation.setOperationArguments(arguments.getInfoArguments());
		operation.execute();
		System.out.println("\nOperation executed!");
	}

	public void registerOperation(Operation operation) {
		operations.put(operation.getCommand().toLowerCase(), operation);
	}

	public void setProgramArguments(ProgramArguments programArguments) {
		this.arguments = programArguments;
	}

	public String buildOptionSyntaxPattern() {
		StringBuilder builder = new StringBuilder();
		for (String tag : operations.keySet()) {
			builder.append(DEFAULT_OPERATION_DELIMITER);
			builder.append(tag).append("|");
		}
		builder.deleteCharAt(builder.lastIndexOf("|"));
		return builder.toString().toLowerCase();
	}

	public boolean isOperationSyntaxValid(String parameters) {
		return parameters.toLowerCase().matches(buildOptionSyntaxPattern());
	}

	public Operation getOperationByTag(String tag) throws NoSuchOperationException {
		Operation operation = operations.get(tag);
		if (operation == null) {
			throw new NoSuchOperationException();
		}
		return operation;
	}

	public String getTagFromOperation(Operation operation) {
		return operation.getCommand();
	}

	public String getTagFromOperationArgument(String operationArgument) {
		return operationArgument.substring(1, operationArgument.length()).toLowerCase();
	}

}
