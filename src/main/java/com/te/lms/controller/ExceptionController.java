package com.te.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.te.lms.exception.LmsException;
import com.te.lms.response.Response;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(LmsException.class)
	public final ResponseEntity<Response> exception(LmsException lmsException) {
		Response response = new Response();
		response.setIsError(true);
		response.setMsg(lmsException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
