package com.te.lms.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.admin.Batch;

public interface BatchDao extends JpaRepository<Batch, Integer> {

	public Batch findByBatchId(String batchId);

	public List<Batch> findAll();
}
