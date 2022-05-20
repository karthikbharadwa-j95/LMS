package com.te.lms.service.admin;

import java.util.List;

import com.te.lms.dto.admin.ApprovalDto;
import com.te.lms.dto.admin.BatchesDto;
import com.te.lms.dto.admin.EmployeeInactiveDto;
import com.te.lms.dto.admin.MentorsDto;
import com.te.lms.dto.admin.SearchById;
import com.te.lms.entity.admin.Batch;
import com.te.lms.entity.admin.Mentor;
import com.te.lms.entity.employee.EmployeePrimaryInfo;

public interface AdminService {

	public Mentor addMentor(Mentor mentorinfo);

	public Mentor updateMentor(Mentor mentor);

	public Boolean deleteMentor(String mentorId);

	public Batch addBatch(Batch batchdetails);

	public Batch updateBatch(Batch batch);

	public Boolean deleteBatch(String batchId);
	
	public List<EmployeeInactiveDto> request();
	
	public Boolean approveEmp(ApprovalDto approval);
	
	public Boolean rejectEmp(ApprovalDto approvalDto);
	
	public List<MentorsDto> allMentors();

	//public List<Mentor> mentorList();
	
	public List<BatchesDto> allBatches();
	
	public SearchById searchById(String id);
}
