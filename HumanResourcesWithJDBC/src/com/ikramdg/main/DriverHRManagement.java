package com.ikramdg.main;

import java.util.List;

import com.ikramdg.domain.Employee;
import com.ikramdg.domain.HRManager;

public class DriverHRManagement {
	
	public static void main(String[] args) {
		
		HRManager manager = new HRManager();
		
		
		
	}

	public static void displayEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		
	}
	
}
