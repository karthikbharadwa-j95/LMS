package com.te.lms.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.admin.SearchById;
import com.te.lms.dto.mentor.AttendanceMorning;
import com.te.lms.dto.mentor.AttendanceNoon;
import com.te.lms.dto.mentor.MentorChangePassword;
import com.te.lms.entity.mentor.MockDetails;
import com.te.lms.response.Response;
import com.te.lms.service.mentor.MentorService;

@RestController
@RequestMapping("/mentor")
public class MentorController {

	@Autowired
	private MentorService mentorService;

	@PostMapping("/attendance")
	public ResponseEntity<Response> attendance(@RequestBody List<AttendanceMorning> attendances) {
		Response response = new Response();
		if (Boolean.TRUE.equals(mentorService.addAttendance(attendances))) {
			response.setIsError(false);
			response.setMsg("Attendance added successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Attendance could not be added");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// mentor updating the attendance
	@PutMapping("/attendance")
	public ResponseEntity<Response> updateAttendance(@RequestBody List<AttendanceNoon> noonAttendances) {
		Response response = new Response();
		if (Boolean.TRUE.equals(mentorService.updateAttendance(noonAttendances))) {
			response.setIsError(false);
			response.setMsg("Attendance updated for noon!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Attendance for Noon was not updated!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/mock")
	public ResponseEntity<Response> addMock(@RequestBody MockDetails mockDetails) {
		Response response = new Response();
		MockDetails Mdetails = mentorService.addMock(mockDetails);
		if (Mdetails != null) {
			response.setIsError(false);
			response.setMsg("Mock added Successfully!");
			response.setData(Mdetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Mock was not added!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/mock")
	public ResponseEntity<Response> updateMock(@RequestBody MockDetails mockDetails) {
		Response response = new Response();
		MockDetails Mdetails = mentorService.updateMock(mockDetails);
		if (Mdetails != null) {
			response.setIsError(false);
			response.setMsg("Mock updated Successfully!");
			response.setData(Mdetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Mock was not updated!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// mentor searching by IDs
	@GetMapping("/search/{id}")
	public ResponseEntity<Response> search(@PathVariable String id) {
		SearchById searchById = mentorService.searchById(id);
		Response response = new Response();
		if (searchById != null) {
			response.setIsError(false);
			response.setMsg("Employee/Mentor found ");
			response.setData(searchById);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Employee/Mentor not found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// mentor updating password

	@PutMapping("/updatePassword")
	public ResponseEntity<Response> changePassword(@RequestBody MentorChangePassword mentorChangePassword) {
		MentorChangePassword emp = mentorService.changePassword(mentorChangePassword);
		Response response = new Response();
		if (emp != null) {
			response.setIsError(false);
			response.setMsg("Password was changed successfully for Mentor Id:" + mentorChangePassword.getMentorId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Please enter the correct credentials!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
