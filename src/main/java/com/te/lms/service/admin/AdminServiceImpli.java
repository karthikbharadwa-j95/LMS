package com.te.lms.service.admin;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.te.lms.dao.admin.BatchDao;
import com.te.lms.dao.admin.MentorDao;
import com.te.lms.dao.admin.RequestListDao;
import com.te.lms.dao.employee.EmployeeDao;
import com.te.lms.dao.security.UserDao;
import com.te.lms.dto.admin.ApprovalDto;
import com.te.lms.dto.admin.BatchesDto;
import com.te.lms.dto.admin.EmployeeInactiveDto;
import com.te.lms.dto.admin.MentorsDto;
import com.te.lms.dto.admin.SearchById;
import com.te.lms.entity.admin.Batch;
import com.te.lms.entity.admin.EmployeeRegistrationRequestList;
import com.te.lms.entity.admin.Mentor;
import com.te.lms.entity.employee.EmployeePrimaryInfo;
import com.te.lms.entity.security.User;
import com.te.lms.enums.BatchStatus;
import com.te.lms.enums.Status;
import com.te.lms.exception.LmsException;
import com.te.lms.generatepassword.GeneratePassword;
import com.te.lms.service.email.EmailService;

@Service
public class AdminServiceImpli implements AdminService {

	@Autowired
	private MentorDao mentorDao;

	@Autowired
	private BatchDao batchDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private RequestListDao requestListDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Mentor addMentor(Mentor mentorinfo) {

		GeneratePassword pwd = new GeneratePassword();
		String tempPassword = pwd.passwordGenerator(10);
		mentorinfo.setPassword(tempPassword);
		Mentor mentor1 = mentorDao.save(mentorinfo);
		User user = new User();
		user.setUserName(mentorinfo.getMentorName());
		user.setUserPassword(tempPassword);
		user.setUserRoles("ROLE_MENTOR");
		userDao.save(user);
		emailService.sendEmail(mentor1.getMentorEmail(), "Spring Test Password",
				"new temporary Password: " + mentor1.getPassword());
		return mentor1;
	}

	@Override
	public Mentor updateMentor(Mentor mentor) {
		return mentorDao.save(mentor);
	}

	@Override
	public Boolean deleteMentor(String mentorId) {
		Mentor mentor = mentorDao.findByMentorId(mentorId);
		if (mentor != null) {
			mentor.setMentorStatus(true);
			mentorDao.save(mentor);
			return true;
		} else {
			throw new LmsException("Mentor not found!");
		}
	}

	@Override
	public Batch addBatch(Batch batchdetails) {
		return batchDao.save(batchdetails);
	}

	@Override
	public Batch updateBatch(Batch batch) {
		return batchDao.save(batch);
	}

	@Override
	public Boolean deleteBatch(String batchId) {
		Batch batch = batchDao.findByBatchId(batchId);
		if (batch != null) {
			batch.setBatchStatus(BatchStatus.TERMINATED);
			batchDao.save(batch);
			return true;
		} else {
			throw new LmsException("Batch not found!");
		}
	}

	// converting entity to dto for inactive employees

	public EmployeeInactiveDto convertEntityToDto(EmployeePrimaryInfo employeePrimaryInfo) {
		EmployeeInactiveDto dto = new EmployeeInactiveDto();
		dto.setEmpId(employeePrimaryInfo.getEmpId());
		dto.setEmpName(employeePrimaryInfo.getEmpName());
		dto.setEmpStatus(employeePrimaryInfo.getEmpStatus());
		dto.setEmployeeEducationDetailsInfo(employeePrimaryInfo.getEducationDetailsInfos());
		dto.setEmployeeContactInfo(employeePrimaryInfo.getEmployeeContactInfos());
		return dto;
	}

	@Override
	public List<EmployeeInactiveDto> request() {
		return employeeDao.findByEmpStatus().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	// converting entity to dto for employee approval
	@Override
	public Boolean approveEmp(ApprovalDto approval) {
		GeneratePassword password = new GeneratePassword();
		String passwordGenerator = password.passwordGenerator(10);
		Batch findByBatchId = batchDao.findByBatchId(approval.getBatchId());
		if (findByBatchId != null) {
			EmployeePrimaryInfo employeePrimaryInfo = employeeDao.findByEmpId(approval.getEmpId());
			employeePrimaryInfo.setEmpPassword(passwordGenerator);
			User user = new User();
			user.setUserPassword(passwordGenerator);
			user.setUserName(approval.getEmpName());
			user.setUserRoles("ROLE_EMPLOYEE");
			userDao.save(user);
			EmployeeRegistrationRequestList requestList = new EmployeeRegistrationRequestList();
			requestList.setBatchId(approval.getBatchId());
			requestList.setBatchName(approval.getBatchName());
			requestList.setIsRejected(0);
			requestList.setRejectionReason(null);
			approval.setEmpName(employeePrimaryInfo.getEmpName());
			employeePrimaryInfo.setEmpStatus(Status.ACTIVE);
			employeePrimaryInfo.setInBatch(findByBatchId);
			employeeDao.save(employeePrimaryInfo);
			requestList.setEmployee(employeePrimaryInfo);
			requestListDao.save(requestList);
			String email = requestList.getEmployee().getEmpMail();
			emailService.sendEmail(email, "Registration Approval Mail",
					"You have been registered to the company. your temporary password is : " + passwordGenerator);
			return true;

		} else {
			throw new LmsException("Employee not approved, and batch was not assigned!");
		}
	}

	// converting entity to dto for employee rejection
	@Override
	public Boolean rejectEmp(ApprovalDto approvalDto) {
		EmployeePrimaryInfo employeePrimaryInfo = employeeDao.findByEmpId(approvalDto.getEmpId());
		String email = employeePrimaryInfo.getEmpMail();
//		employeeDao.delete(employeePrimaryInfo);
		EmployeeRegistrationRequestList requestList = new EmployeeRegistrationRequestList();
		requestList.setIsRejected(1);
		requestList.setRejectionReason(approvalDto.getRejectionReason());
		requestList.setEmployee(employeePrimaryInfo);
		requestList.setEmployee(employeePrimaryInfo);
		requestListDao.save(requestList);
		emailService.sendEmail(email, "Rejection Mail",
				"We are sorry to inform that your registration could not be approved.");
		return true;
	}

//	@Override
//	public List<Mentor> mentorList() {
//		return mentorDao.findAll();
//	}

	@Override
	public List<MentorsDto> allMentors() {
		return mentorDao.findAll().stream().map(this::convertMentorDto).collect(Collectors.toList());
	}

	public MentorsDto convertMentorDto(Mentor mentor) {
		MentorsDto allMentorsDto = new MentorsDto();
		allMentorsDto.setMentorEmail(mentor.getMentorEmail());
		allMentorsDto.setMentorId(mentor.getMentorId());
		allMentorsDto.setMentorName(mentor.getMentorName());
		allMentorsDto.setTechnicalSkills(mentor.getTechnicalSkills());
		return allMentorsDto;
	}

	public BatchesDto convertBatchDto(Batch batch) {
		BatchesDto allBatchesDto = new BatchesDto();
		allBatchesDto.setBatchId(batch.getBatchId());
		allBatchesDto.setBatchName(batch.getBatchName());
		allBatchesDto.setBatchEndDate(batch.getBatchEndDate());
		allBatchesDto.setBatchStartDate(batch.getBatchStartDate());
		allBatchesDto.setBatchStatus(batch.getBatchStatus());
		allBatchesDto.setBatchTechnology(batch.getBatchTechnology());
		allBatchesDto.setMentorName(batch.getBatchMentor().getMentorName());
		return allBatchesDto;
	}

	@Override
	public List<BatchesDto> allBatches() {
		return batchDao.findAll().stream().map(this::convertBatchDto).collect(Collectors.toList());
	}

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

}
