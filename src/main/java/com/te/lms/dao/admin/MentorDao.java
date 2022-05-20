package com.te.lms.dao.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.te.lms.entity.admin.Mentor;

public interface MentorDao extends JpaRepository<Mentor, String> {

	public Mentor findByMentorId(String mentorId);

	public List<Mentor> findAll();
}
