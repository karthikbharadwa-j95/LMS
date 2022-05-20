package com.te.lms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.admin.ApprovalDto;
import com.te.lms.dto.admin.BatchesDto;
import com.te.lms.dto.admin.EmployeeInactiveDto;
import com.te.lms.dto.admin.MentorsDto;
import com.te.lms.dto.admin.SearchById;
import com.te.lms.entity.admin.Batch;
import com.te.lms.entity.admin.Mentor;
import com.te.lms.response.Response;
import com.te.lms.service.admin.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// Admin adding a Mentor
	@PostMapping("/mentor")
	public ResponseEntity<Response> addMentor(@RequestBody Mentor mentor) {
		Response response = new Response();
		Mentor addMentor = adminService.addMentor(mentor);

		if (addMentor != null) {
			response.setIsError(false);
			response.setMsg("Data added successful");
			response.setData(addMentor);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Data was not added successfully!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	// Admin updating the Mentor Details
	@PutMapping("/mentor")
	public ResponseEntity<Response> updateMentor(@RequestBody Mentor mentor) {
		Response response = new Response();
		Mentor addMentor = adminService.addMentor(mentor);

		if (addMentor != null) {
			response.setIsError(false);
			response.setMsg("Mentor updated successful");
			response.setData(addMentor);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Mentor was not updated!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	// admin deleting the mentor
	@DeleteMapping("/mentor/{mentorId}")
	public ResponseEntity<Response> deleteMentor(@PathVariable String mentorId) {
		Response response = new Response();
		if (Boolean.TRUE.equals(adminService.deleteMentor(mentorId))) {
			response.setIsError(false);
			response.setMsg("Mentor deleted successfully!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Mentor was not deleted!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	// Admin adding a Batch
	@PostMapping("/batch")
	public ResponseEntity<Response> addBatch(@RequestBody Batch batch) {
		Response response = new Response();
		Batch addBatch = adminService.addBatch(batch);
		if (addBatch != null) {
			response.setIsError(false);
			response.setMsg("Batch added successfully!");
			response.setData(addBatch);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Batch was not added!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// admin updating the Batch Details
	@PutMapping("/batch")
	public ResponseEntity<Response> updateBatch(@RequestBody Batch batch) {
		Response response = new Response();
		Batch updateBatch = adminService.updateBatch(batch);

		if (updateBatch != null) {
			response.setIsError(false);
			response.setMsg("Batch updated successful");
			response.setData(updateBatch);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Batch was not updated!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	// admin deleting the batch
	@DeleteMapping("/batch/{batchId}")
	public ResponseEntity<Response> deleteBatch(@PathVariable String batchId) {
		Response response = new Response();
		// below code by the sonar lint
		if (Boolean.TRUE.equals(adminService.deleteBatch(batchId))) {
			response.setIsError(false);
			response.setMsg("Batch deleted successfully!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Batch was not deleted!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// admin viewing list of inactive employees
	@GetMapping("/employee")
	public ResponseEntity<Response> getApproval() {
		Response response = new Response();
		List<EmployeeInactiveDto> employeeInactiveDtos = adminService.request();
		if (employeeInactiveDtos != null) {
			response.setIsError(false);
			response.setMsg("The employees to be approved are : ");
			response.setData(employeeInactiveDtos);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Employees not found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// admin approving the employee
	@PostMapping("/employee")
	public ResponseEntity<Response> approval(@RequestBody ApprovalDto approval) {
		Response response = new Response();
		if (Boolean.TRUE.equals(adminService.approveEmp(approval))) {
			response.setIsError(false);
			response.setMsg("Employee Approved");
			response.setData(approval);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Error");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// admin rejecting the employee

	@PostMapping("/employeeReject")
	public ResponseEntity<Response> rejection(@RequestBody ApprovalDto requestList) {
		Response response = new Response();
		if (Boolean.TRUE.equals(adminService.rejectEmp(requestList))) {
			response.setIsError(false);
			response.setMsg("Rejection done");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Error");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// admin viewing mentors

	@GetMapping("/mentors")
	public ResponseEntity<Response> mentors() {
		List<MentorsDto> allMentors = adminService.allMentors();

		Response response = new Response();
		if (allMentors != null) {
			response.setIsError(false);
			response.setMsg("The list 0f mentors : ");
			response.setData(allMentors);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("Mentors not found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// list of batches
	@GetMapping("/batches")
	public ResponseEntity<Response> batchlist() {
		List<BatchesDto> listofbatches = adminService.allBatches();
		Response response = new Response();
		if (listofbatches != null) {
			response.setIsError(false);
			response.setMsg("displaying the list of batches");
			response.setData(listofbatches);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setIsError(true);
			response.setMsg("no batches to display!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// admin searching by IDs
	@GetMapping("/search/{id}")
	public ResponseEntity<Response> search(@PathVariable String id) {
		SearchById searchById = adminService.searchById(id);
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
}
