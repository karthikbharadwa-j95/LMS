package com.te.lms.entity.admin;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.te.lms.entity.employee.EmployeeTechnicalSkillsInfo;
import com.te.lms.entity.mentor.MockDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "mentor")
public class Mentor {
	
	@Id
	@Column(name="mentor_id")
	private String mentorId;
	
	@Column(name="mentor_email")
	private String mentorEmail;
	
	@Column(name="last_login")
	private LocalDateTime lastLogin;
	
	@Column(name="mentor_name")
	private String mentorName;
	
	@Column(name = "mentor_status")
	private Boolean mentorStatus;
	
	@Column(name="password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name="username")
	private String username;
	
	@Column(name="technical_skills")
	private String technicalSkills;
	
	
//	@ManyToMany(cascade = CascadeType.ALL)//, mappedBy = "mentors")
//	@JoinTable(name = "mentor_technical_skills",
//	joinColumns = @JoinColumn(name = "mentor_mentor_id"), 
//	inverseJoinColumns = @JoinColumn(name = "technical_skills_skill_id"))
//	private List<EmployeeTechnicalSkillsInfo> employeeTechnicalSkillsInfos;
	
//	@ManyToMany(cascade = CascadeType.ALL)//, mappedBy = "mentors1")
//	@JoinTable(name = "mock_details_mentor", 
//	joinColumns = @JoinColumn(name = "mentor_mentor_id"), 
//	inverseJoinColumns = @JoinColumn(name = "mock_details_mock_id"))
//	private List<MockDetails> mockDetails;
	
}