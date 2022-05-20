package com.te.lms.dao.mentor;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.employee.EmployeePrimaryInfo;
import com.te.lms.entity.mentor.MockDetails;

public interface MockDao extends JpaRepository<MockDetails, Integer> {

	public MockDetails findByMockNo(Integer mockNo);
}
