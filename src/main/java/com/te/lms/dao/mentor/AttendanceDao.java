package com.te.lms.dao.mentor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.mentor.EmployeeAttendance;

public interface AttendanceDao extends JpaRepository<EmployeeAttendance, Integer>{

	public EmployeeAttendance findByAttendanceId(Integer attendanceId);
	
	public EmployeeAttendance findByEmployee(String empId);
	
	public List<EmployeeAttendance> findByAttendanceDate(LocalDate attendanceDate);
}
