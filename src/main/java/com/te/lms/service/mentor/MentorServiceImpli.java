package com.te.lms.service.mentor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lms.dao.mentor.MockDao;
import com.te.lms.dao.security.UserDao;
import com.te.lms.dao.admin.BatchDao;
import com.te.lms.dao.admin.MentorDao;
import com.te.lms.dao.employee.EmployeeDao;
import com.te.lms.dao.mentor.AttendanceDao;
import com.te.lms.dto.admin.SearchById;
import com.te.lms.dto.mentor.AttendanceMorning;
import com.te.lms.dto.mentor.AttendanceNoon;
import com.te.lms.dto.mentor.BatchAttendanceDto;
import com.te.lms.dto.mentor.MentorChangePassword;
import com.te.lms.entity.admin.Mentor;
import com.te.lms.entity.employee.EmployeePrimaryInfo;
import com.te.lms.entity.mentor.EmployeeAttendance;
import com.te.lms.entity.mentor.MockDetails;
import com.te.lms.entity.security.User;
import com.te.lms.exception.LmsException;

@Service
public class MentorServiceImpli implements MentorService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private MockDao mockDao;

	@Autowired
	private MentorDao mentorDao;

	@Autowired
	private BatchDao batchDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AttendanceMorning attendanceMorning;

	@Override
	public MockDetails addMock(MockDetails details) {
		MockDetails mockDetails = mockDao.findByMockNo(details.getMockNo());
		if (mockDetails != null) {
			throw new LmsException("Mock was not added!");
		} else {
			return mockDao.save(details);
		}
	}

	@Override
	public MockDetails updateMock(MockDetails mockDetails) {
		MockDetails updateDetails = mockDao.findByMockNo(mockDetails.getMockNo());
		if (updateDetails != null) {
			return mockDao.save(mockDetails);
		} else {
			throw new LmsException("Could not update the mock details!");
		}

	}

	// mentor searching by IDs
	@Override
	public SearchById searchById(String id) {
		SearchById searchById = new SearchById();
		Mentor findByMentorId = mentorDao.findByMentorId(id);
		if (findByMentorId != null) {
			searchById.setId(findByMentorId.getMentorId());
			searchById.setEmpDesignation("mentor");
			searchById.setName(findByMentorId.getMentorName());
			searchById.setSkills(findByMentorId.getTechnicalSkills());
			searchById.setEmail(findByMentorId.getMentorEmail());
			return searchById;
		} else {
			EmployeePrimaryInfo findByEmpId = employeeDao.findByEmpId(id);
			if (findByEmpId != null) {
				searchById.setEmail(findByEmpId.getEmpMail());
				searchById.setEmpDesignation(findByEmpId.getEmpDesignation());
				searchById.setId(findByEmpId.getEmpId());
				searchById.setEmpStatus(findByEmpId.getEmpStatus());
				searchById.setName(findByEmpId.getEmpName());
				searchById.setEmpGender(findByEmpId.getEmpGender());
				return searchById;
			} else {
				throw new LmsException("Employee not found!");
			}
		}
	}

	@Override
	public MentorChangePassword changePassword(MentorChangePassword password) {
		Mentor mentor = mentorDao.findByMentorId(password.getMentorId());
		if (mentor.getPassword().equals(password.getCurrentPassword())) {
			if (password.getNewPassword().equals(password.getConfirmPassword())) {
				mentor.setPassword(password.getConfirmPassword());
				mentorDao.save(mentor);
		//	User user = new User();
				User findByUserName = userDao.findByUserName(password.getUsername());
				findByUserName.setUserPassword(password.getConfirmPassword());
				//findByUserName.setUserId(findByUserName.getUserId());
				userDao.save(findByUserName);
			} else {
				throw new LmsException("New Passwords does not match!");
			}
		} else {
			throw new LmsException("Please enter the correct old password!");
		}
		return password;

	}

	@Override
	public Boolean addAttendance(List<AttendanceMorning> attendances) {
		List<EmployeeAttendance> addAttendences = new ArrayList<EmployeeAttendance>();
		for (AttendanceMorning employeeAttendance : attendances) {
			EmployeeAttendance attend = new EmployeeAttendance();
			attend.setAttendanceDate(employeeAttendance.getAttendenceDate());
			attend.setAttendanceMorning(employeeAttendance.getAttendenceMorning());
			attend.setEmployee(employeeDao.findByEmpId(employeeAttendance.getEmpId()));
			addAttendences.add(attend);
		}
//		System.out.println(addAttendences);
		for (EmployeeAttendance employeeAttendance : addAttendences) {
			attendanceDao.save(employeeAttendance);
		}
		return true;
	}

	@Override
	public Boolean updateAttendance(List<AttendanceNoon> noonAttendance) {
		for (AttendanceNoon employeeAttendance1 : noonAttendance) {
			List<EmployeeAttendance> addNoonAttendance = attendanceDao
					.findByAttendanceDate(employeeAttendance1.getAttendenceDate());
			for (EmployeeAttendance attendance : addNoonAttendance) {
				attendance.setAttendanceNoon(employeeAttendance1.getAttendanceNoon());
				attendance.setAttendanceMorning(attendance.getAttendanceMorning());
				attendance.setEmployee(employeeDao.findByEmpId(employeeAttendance1.getEmpId()));
				attendance.setAttendanceDate(employeeAttendance1.getAttendenceDate());
				attendance.setAttendanceId(attendance.getAttendanceId());
				attendanceDao.save(attendance);
			}

		}
//		List<EmployeeAttendance> att = new ArrayList<EmployeeAttendance>();
//		att.add(attend);
//		att.add(noonAttend);
//		for (EmployeeAttendance employeeAttendance1 : addNoonAttendance) {
//			attendanceDao.save(employeeAttendance1);
//		}
//		for (EmployeeAttendance employeeAttendances : att) {
//			attendanceDao.saveAll(att);
//		}
		return true;
	}

	@Override
	public List<BatchAttendanceDto> getBatchAttendance(BatchAttendanceDto batchAttendanceDto) {
		List<BatchAttendanceDto> attendanceDtos = new ArrayList<>();
		List<EmployeePrimaryInfo> employeePrimaryInfos = employeeDao
				.findByInBatch(batchDao.findByBatchId(batchAttendanceDto.getBatchId()));
		for (EmployeePrimaryInfo employeePrimaryInfo : employeePrimaryInfos) {
			List<EmployeeAttendance> attendances = attendanceDao
					.findByAttendanceDate(batchAttendanceDto.getAttendanceDate());
			BatchAttendanceDto attendanceDto = new BatchAttendanceDto();
			if (attendances != null) {
				for (EmployeeAttendance employeeAttendance : attendances) {
					attendanceDto.setAttendanceMorning(employeeAttendance.getAttendanceMorning());
					attendanceDto.setAttendanceNoon(employeeAttendance.getAttendanceNoon());
				}
			}
			attendanceDto.setEmpName(employeePrimaryInfo.getEmpName());
			attendanceDto.setEmpId(employeePrimaryInfo.getEmpId());
			attendanceDtos.add(attendanceDto);
		}
		return attendanceDtos;
	}

}
