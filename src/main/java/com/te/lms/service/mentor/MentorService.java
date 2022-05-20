package com.te.lms.service.mentor;

import java.util.List;

import com.te.lms.dto.admin.SearchById;
import com.te.lms.dto.mentor.AttendanceMorning;
import com.te.lms.dto.mentor.AttendanceNoon;
import com.te.lms.dto.mentor.MentorChangePassword;
import com.te.lms.entity.mentor.EmployeeAttendance;
import com.te.lms.entity.mentor.MockDetails;

public interface MentorService {

	public Boolean addAttendance(List<AttendanceMorning> attendances);
	
	public Boolean updateAttendance(List<AttendanceNoon> noonAttendance);

	public MockDetails addMock(MockDetails details);

	public MockDetails updateMock(MockDetails mockDetails);
	
	public SearchById searchById(String id);
	
	public MentorChangePassword changePassword(MentorChangePassword password);
}
