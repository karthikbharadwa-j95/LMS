package com.te.lms.dto.mentor;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AttendanceMorning {

	private Integer attendanceId;
	private LocalDate attendenceDate;
	private Integer attendenceMorning;
	private String empId;

}
