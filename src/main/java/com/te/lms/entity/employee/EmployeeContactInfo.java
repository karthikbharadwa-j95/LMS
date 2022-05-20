package com.te.lms.entity.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.te.lms.enums.ContactType;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_contact_info")
public class EmployeeContactInfo {
	@Id
	@Column(name="contact_number")
	private Long contactNumber;
	
	@Column(name="contact_type")
	@Enumerated(EnumType.STRING)
	private ContactType contactType;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	private List<EmployeePrimaryInfo> employeePrimaryInfo4;
	
}