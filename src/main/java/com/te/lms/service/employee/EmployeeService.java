package com.te.lms.service.employee;

import java.util.List;

import com.te.lms.dto.employee.DisplayPrimaryInfo;
import com.te.lms.dto.employee.EmployeeChangePassword;
import com.te.lms.entity.employee.EmployeePrimaryInfo;

public interface EmployeeService {

	public EmployeePrimaryInfo addEmployee(EmployeePrimaryInfo employeePrimaryInfo);

	public void deleteAll();

	public DisplayPrimaryInfo searchById(String empId);

	public EmployeePrimaryInfo empLoginDetails(String empId, String empPassword);

	public EmployeePrimaryInfo updateEmployee(EmployeePrimaryInfo empPrimaryInfo);
	
	public EmployeeChangePassword changePassword(EmployeeChangePassword password);

}
