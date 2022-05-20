package com.te.lms.dto.mentor;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AttendanceNoon {

	private Integer attendanceId;
	private LocalDate attendenceDate;
	private Integer attendanceNoon;
	private String empId;
}
