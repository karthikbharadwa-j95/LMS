package com.te.lms.dto.employee;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.te.lms.enums.EmpBloodGroup;
import com.te.lms.enums.EmpGender;
import com.te.lms.enums.EmpNationality;
import com.te.lms.enums.Status;

import lombok.Data;

@Data
@Component
public class DisplayPrimaryInfo {

	
	private String empId;
	private EmpBloodGroup empBloodGroup;
	private LocalDate empDob;
	private LocalDate empDoj;
	private String empDesignation;
	private String degree;
	private String empMail;
	private String empName;
	private EmpGender empGender;
	private EmpNationality empNationality;
	private Status empStatus;

}
