package com.te.lms.entity.mentor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.te.lms.entity.admin.Mentor;
import com.te.lms.enums.MockPanel;
import com.te.lms.enums.MockRating;
import com.te.lms.enums.MockTechnology;
import com.te.lms.enums.MockType;

import lombok.Data;

@Data
@Entity
@Table(name = "mock_details")
public class MockDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "mock_no")
	private Integer mockNo;

	@Column(name = "mock_date")
	private LocalDateTime mockDate;

	@Column(name = "mock_feedback")
	private String mockFeedback;

	@Column(name = "mock_panel")
	@Enumerated(EnumType.STRING)
	private MockPanel mockPanel;

	@Column(name = "mock_technology")
	@Enumerated(EnumType.STRING)
	private MockTechnology mockTechnology;

	@Column(name = "mock_rating")
	@Enumerated(EnumType.STRING)
	private MockRating mockRating;

	@Column(name = "mock_type")
	@Enumerated(EnumType.STRING)
	private MockType mockType;

	@Column(name = "practical")
	private Integer practical;

	@Column(name = "theoritical")
	private Integer theoritical;

	@Column(name = "employee_emp_id")
	private String employeeEmpId;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "mentor_details_mentor", 
//	joinColumns = @JoinColumn(name = "mock_details_mock_id"), 
//	inverseJoinColumns = @JoinColumn(name = "mentor_mentor_id"))
//	private List<Mentor> mentors1;

}