package com.te.lms.dto.mentor;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BatchAttendanceDto {
	private String batchId;
	private String empId;
	private String empName;
	private Integer attendanceMorning;
	private Integer attendanceNoon;
	private LocalDate attendanceDate;
}
