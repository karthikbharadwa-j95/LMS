package com.te.lms.entity.mentor;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.te.lms.entity.employee.EmployeePrimaryInfo;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_attendance")
public class EmployeeAttendance {

	@Id
	@Column(name = "attendance_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer attendanceId;
	@Column(name = "attendance_morning")
	private Integer attendanceMorning;
	@Column(name = "attendance_noon")
	private Integer attendanceNoon;
	@Column(name = "attendance_date")
	private LocalDate attendanceDate;
	
	
	@OneToOne
	private EmployeePrimaryInfo  employee;
	
}
