package com.te.lms.dto.employee;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeChangePassword {

	private String empId;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
}
