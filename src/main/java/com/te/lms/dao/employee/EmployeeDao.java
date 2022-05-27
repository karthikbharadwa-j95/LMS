package com.te.lms.dao.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.admin.Batch;
import com.te.lms.entity.employee.EmployeePrimaryInfo;

@Repository
public interface EmployeeDao extends JpaRepository<EmployeePrimaryInfo, String> {

	public EmployeePrimaryInfo findByEmpId(String empId);

	@Query("from EmployeePrimaryInfo where empStatus in ('INACTIVE')")
	public List<EmployeePrimaryInfo> findByEmpStatus();

	public List<EmployeePrimaryInfo> findByInBatch(Batch inBatch);
}
