package com.te.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.employee.DisplayPrimaryInfo;
import com.te.lms.dto.employee.EmployeeChangePassword;
import com.te.lms.entity.employee.EmployeePrimaryInfo;
import com.te.lms.response.Response;
import com.te.lms.service.employee.EmployeeService;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// to add the employee into the DB
	@PostMapping("/register")
	public ResponseEntity<Response> addEmp(@RequestBody EmployeePrimaryInfo empPrimaryInfo) {
		Response response = new Response();
		EmployeePrimaryInfo employee = employeeService.addEmployee(empPrimaryInfo);
		if (employee != null) {
			response.setIsError(false);
			response.setMsg("Data added successful");
			response.setEmployeePrimaryInfo(employee);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Data was not added successfully!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// to delete the entire employee details in the DB
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteAll() {
		Response response = new Response();
		employeeService.deleteAll();
		response.setMsg("Data deleted Successfully!");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	// to update the employee
	@PutMapping("/update")
	public ResponseEntity<Response> updateEmp(@RequestBody EmployeePrimaryInfo empPrimaryInfo) {
		Response response = new Response();
		EmployeePrimaryInfo employee = employeeService.updateEmployee(empPrimaryInfo);
		if (employee != null) {
			response.setIsError(false);
			response.setMsg("Data updated successful");
			response.setEmployeePrimaryInfo(employee);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Data was not added successfully!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// to retrieve only the primary info without few fields
	@GetMapping("/search/{empId}")
	public ResponseEntity<Response> displayPrimaryInfo(@PathVariable String empId) {
		Response response = new Response();
		DisplayPrimaryInfo displayPrimaryInfo = employeeService.searchById(empId);
		if (displayPrimaryInfo != null) {
			response.setIsError(false);
			response.setMsg("Details of " + empId);
			response.setData(displayPrimaryInfo);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			response.setIsError(true);
			response.setMsg("Details of " + empId + " not found!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// displaying employee profile after logging in
	@GetMapping("/employee")
	public ResponseEntity<Response> empLoginDetails(@RequestBody EmployeePrimaryInfo primaryInfo) {
		Response response = new Response();
		EmployeePrimaryInfo loginDetails = employeeService.empLoginDetails(primaryInfo.getEmpId(),
				primaryInfo.getEmpPassword());
		if (loginDetails != null) {
			response.setIsError(false);
			response.setMsg("Details of " + loginDetails.getEmpId() + " are");
			response.setData(loginDetails);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			response.setIsError(true);
			response.setMsg("Details of " + loginDetails.getEmpId() + " not found!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// employee changing password
	@PutMapping("/updatePassword")
	public ResponseEntity<Response> changePassword(@RequestBody EmployeeChangePassword employeeChangePassword) {
		EmployeeChangePassword emp = employeeService.changePassword(employeeChangePassword);
		Response response = new Response();
		if (emp != null) {
			response.setIsError(false);
			response.setMsg("Password was changed successfully for empId:" + employeeChangePassword.getEmpId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Please enter the correct credentials!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
