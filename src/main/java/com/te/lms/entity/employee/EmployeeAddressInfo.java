package com.te.lms.entity.employee;

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

import com.te.lms.enums.AddressType;
import com.te.lms.enums.State;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_address_info")
public class EmployeeAddressInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;
	
	@Column(name = "address_type")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "door_no")
	private String doorNo;
	
	@Column(name = "landmark")
	private String landmark;
	
	@Column(name = "locality")
	private String locality;
	
	@Column(name = "pincode")
	private Integer pincode;
	
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state;
	
	@Column(name = "street")
	private String street;

//	@ManyToMany(cascade = CascadeType.ALL)
//	private List<EmployeePrimaryInfo> employeePrimaryInfo2;
	
}