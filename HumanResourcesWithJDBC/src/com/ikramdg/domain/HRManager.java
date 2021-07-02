package com.ikramdg.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ikramdg.exceptions.DepartmentNotFoundException;
import com.ikramdg.exceptions.EmployeeNotFoundException;
import com.ikramdg.util.DBConnector;

public class HRManager {

	private static Connection connection;
	private static Statement statement;
	private static ResultSet allEmployeesResultSet;
	private static ResultSet allDepartmentsResultSet;

	private final static String GET_ALL_DEPARTMENTS_SQL = "SELECT * FROM t_department ORDER BY department_id ASC";
	private final static String GET_ALL_EMPLOYEES_SQL = "SELECT * FROM t_employee ORDER BY employee_id ASC";
	static {
		try {
//			connection = DBConnector.connect();
//			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			
//			allEmployeesResultSet = statement.executeQuery(GET_ALL_EMPLOYEES_SQL);
//			allDepartmentsResultSet = statement.executeQuery(GET_ALL_DEPARTMENTS_SQL);
			
			allEmployeesResultSet = connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery(GET_ALL_EMPLOYEES_SQL);
			
			allDepartmentsResultSet = connection
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery(GET_ALL_DEPARTMENTS_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addDepartment(Department department) throws SQLException {
		allDepartmentsResultSet.moveToInsertRow();
		try {
			allDepartmentsResultSet.moveToInsertRow();
			allDepartmentsResultSet.updateInt("department_id", department.getDepartmentId());
			allDepartmentsResultSet.updateString("department_name", department.getDepartmentName());
			allDepartmentsResultSet.insertRow();

		} finally {
			allDepartmentsResultSet.moveToCurrentRow();
		}
	}

	/**
	 * @param department
	 * @return true if department can be deleted
	 * @throws SQLException
	 */
	public boolean deleteDepartment(Department department) throws SQLException {
		return deleteDepartment(department.getDepartmentId());
	}

	public boolean deleteDepartment(int departmentId) throws SQLException {
		boolean isDeleted = false;
		allDepartmentsResultSet.beforeFirst();
		while (allDepartmentsResultSet.next()) {
			if (allDepartmentsResultSet.getInt("department_id") == departmentId) {
				allDepartmentsResultSet.deleteRow();
				isDeleted = true;
			}
		}
		return isDeleted;
	}
	
	public void updateEmployee(Employee employee,Department newDepartment) throws SQLException, EmployeeNotFoundException {
		allEmployeesResultSet.beforeFirst();
		while(allEmployeesResultSet.next()) {
			if(allEmployeesResultSet.getInt("employee_id") == employee.getEmployeeId()) {
				allEmployeesResultSet.updateInt("department_id", newDepartment.getDepartmentId());
				allEmployeesResultSet.updateRow();
				return;
			}
		}
		throw new EmployeeNotFoundException();
	}

	public void addEmployee(Employee employee) throws SQLException {
		allEmployeesResultSet.moveToInsertRow();
		try {
			allEmployeesResultSet.updateInt("employee_id", employee.getEmployeeId());
			allEmployeesResultSet.updateString("first_name", employee.getFirstName());
			allEmployeesResultSet.updateString("last_name", employee.getLastName());
			allEmployeesResultSet.updateInt("department_id", employee.getDepartment().getDepartmentId());
			allEmployeesResultSet.insertRow();
		} finally {
			allEmployeesResultSet.moveToCurrentRow();
		}
	}

	public Employee getEmployee(int employeeId)
			throws SQLException, DepartmentNotFoundException, EmployeeNotFoundException {
		Employee employee = new Employee();
		allEmployeesResultSet.beforeFirst();
		while (allEmployeesResultSet.next()) {
			if (allEmployeesResultSet.getInt("employee_id") == employeeId) {
				employee.setEmployeeId(employeeId);
				employee.setFirstName(allEmployeesResultSet.getString("first_name"));
				employee.setLastName(allEmployeesResultSet.getString("last_name"));
				employee.setSalary(5000.0); // employee tablosunda henüz salary kolonu yok
				employee.setDepartment(getDepartment(allEmployeesResultSet.getInt("department_id")));
				return employee;
			}
		}
		throw new EmployeeNotFoundException();
	}

	public Department getDepartment(int departmentId) throws SQLException, DepartmentNotFoundException {
		Department department = new Department();
		allDepartmentsResultSet.beforeFirst();
		while (allDepartmentsResultSet.next()) {
			if (allDepartmentsResultSet.getInt("department_id") == departmentId) {
				department.setDepartmentId(departmentId);
				department.setDepartmentName(allDepartmentsResultSet.getString("department_name"));
				return department;
			}
		}
		throw new DepartmentNotFoundException();
	}

	public List<Employee> getAllEmployees()
			throws SQLException, DepartmentNotFoundException, EmployeeNotFoundException {
		List<Employee> employees = new ArrayList<Employee>();
		allEmployeesResultSet.beforeFirst();
		while (allEmployeesResultSet.next()) {
			employees.add(getEmployee(allEmployeesResultSet.getInt("employee_id")));
		}
		return employees;
	}
	
	/**
	 * Row sıralamaları değişebileceğinden employeeler eksik gelebilir 
	 */
	public List<Employee> getAllEmployeesWORKING_ON()
			throws SQLException, DepartmentNotFoundException, EmployeeNotFoundException {
		List<Employee> employees = new ArrayList<Employee>();
		allEmployeesResultSet.beforeFirst();
		while (allEmployeesResultSet.next()) {
//			Employee employee = new Employee();
//			employee.setEmployeeId(allEmployeesResultSet.getInt("employee_id"));
//			employee.setFirstName(allEmployeesResultSet.getString("first_name"));
//			employee.setLastName(allEmployeesResultSet.getString("last_name"));
//			employee.setSalary(5000.0); // employee tablosunda henüz salary kolonu yok
//			employee.setDepartment(getDepartment(allEmployeesResultSet.getInt("department_id")));
//			employees.add(employee);

//			int employeeId = allEmployeesResultSet.getInt("employee_id");
			employees.add(getEmployee(allEmployeesResultSet.getInt("employee_id"))); // yalnızca 1. satırı çalıştırır üstteki metot beforefirst yaptı...
//			System.out.println(allEmployeesResultSet.getRow()); getRow ile absolute row a gidilebilir. sonuçta burada kullanılan resultSet metot boyunca aynı kalacak. getEmployee() classında farklı bir employeeResultSet kullanımına karşı hataları engellemiş oluruz.
//			allEmployeesResultSet.previous();
//			allEmployeesResultSet.first();
//			while(allEmployeesResultSet.getInt("employee_id") != employeeId) {
//				allEmployeesResultSet.next();
//			}
		}
		return employees;
	}

}
