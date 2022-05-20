package com.te.lms.service.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lms.dao.employee.EmployeeDao;
import com.te.lms.dto.employee.DisplayPrimaryInfo;
import com.te.lms.dto.employee.EmployeeChangePassword;
import com.te.lms.entity.employee.EmployeePrimaryInfo;
import com.te.lms.exception.LmsException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DisplayPrimaryInfo displayPrimaryInfo;

	@Override
	public EmployeePrimaryInfo addEmployee(EmployeePrimaryInfo employeePrimaryInfo) {

		EmployeePrimaryInfo info = employeeDao.findByEmpId(employeePrimaryInfo.getEmpId());
		if (info != null) {
			throw new LmsException("Could not add the employee!");
		} else {
			return employeeDao.save(employeePrimaryInfo);
		}
	}

	@Override
	public void deleteAll() {
		employeeDao.deleteAll();
	}

	@Override
	public DisplayPrimaryInfo searchById(String empId) {
		EmployeePrimaryInfo primaryinfo = employeeDao.findByEmpId(empId);
		displayPrimaryInfo.setEmpId(empId);
		displayPrimaryInfo.setEmpBloodGroup(primaryinfo.getEmpBloodGroup());
		displayPrimaryInfo.setEmpDob(primaryinfo.getEmpDob());
		displayPrimaryInfo.setEmpDoj(primaryinfo.getEmpDoj());
		displayPrimaryInfo.setEmpGender(primaryinfo.getEmpGender());
		displayPrimaryInfo.setEmpDesignation(primaryinfo.getEmpDesignation());
		displayPrimaryInfo.setEmpMail(primaryinfo.getEmpMail());
		displayPrimaryInfo.setEmpName(primaryinfo.getEmpName());
		displayPrimaryInfo.setEmpNationality(primaryinfo.getEmpNationality());
		displayPrimaryInfo.setEmpStatus(primaryinfo.getEmpStatus());
		displayPrimaryInfo.setDegree(primaryinfo.getDegree());

		return displayPrimaryInfo;

	}

	@Override
	public EmployeePrimaryInfo empLoginDetails(String empId, String empPassword) {
		EmployeePrimaryInfo info = employeeDao.findByEmpId(empId);
		if (info != null) {
			if (info.getEmpPassword().equals(empPassword)) {
				return info;
			}
			throw new LmsException("Incorrect Password!");
		} else {
			throw new LmsException("Invalid Credentials!");
		}
	}

	@Override
	public EmployeePrimaryInfo updateEmployee(EmployeePrimaryInfo empPrimaryInfo) {

		EmployeePrimaryInfo info1 = employeeDao.findByEmpId(empPrimaryInfo.getEmpId());
		if (info1 != null) {
			employeeDao.delete(info1);
			return employeeDao.save(empPrimaryInfo);

		}

		throw new LmsException("Invalid Credentials!");
	}

	@Override
	public EmployeeChangePassword changePassword(EmployeeChangePassword password) {
		EmployeePrimaryInfo emp = employeeDao.findByEmpId(password.getEmpId());
		if (emp.getEmpPassword().equals(password.getCurrentPassword())) {
			if (password.getNewPassword().equals(password.getConfirmPassword())) {
				emp.setEmpPassword(password.getConfirmPassword());
				employeeDao.save(emp);
			} else {
				throw new LmsException("New Passwords does not match!");
			}
		} else {
			throw new LmsException("Please enter the correct old password!");
		}
		return password;

	}

}
