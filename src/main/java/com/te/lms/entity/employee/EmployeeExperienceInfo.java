package com.te.lms.entity.employee;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name = "employee_experience_info")
public class EmployeeExperienceInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "experience_id")
	private Integer experienceId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "date_of_joining")
	private LocalDate dateOfJoining;
	
	@Column(name = "date_of_relieving")
	private LocalDate dateOfRelieving;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "years_of_experience")
	private Double yearsOfExperience;

//	@ManyToMany(cascade = CascadeType.ALL)
//	private List<EmployeePrimaryInfo> employeePrimaryInfo1;

}
