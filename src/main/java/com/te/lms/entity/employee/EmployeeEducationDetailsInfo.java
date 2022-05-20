package com.te.lms.entity.employee;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.te.lms.enums.EducationType;
import com.te.lms.enums.State;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_education_details_info")
public class EmployeeEducationDetailsInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "education_id")
	private Integer educationId;
	
	@Column(name = "education_type")
	@Enumerated(EnumType.STRING)
	private EducationType  educationType;
	
	@Column(name = "institute_name")
	private String instituteName;
	
	@Column(name = "percentage")
	private Double percentage;
	
	@Column(name = "specialization")
	private String specialization;
	
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state;
	
	@Column(name = "university_name")
	private String universityName;
	
	@Column(name = "year_of_passing")
	private Integer yearOfPassing;

//	@ManyToMany(cascade = CascadeType.ALL)
//	private List<EmployeePrimaryInfo> employeePrimaryInfo3;

	
}
