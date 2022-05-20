package com.te.lms.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.te.lms.dto.employee.DisplayPrimaryInfo;
import com.te.lms.entity.employee.EmployeePrimaryInfo;

import lombok.Data;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable{
	
	private Boolean isError;
	private String msg;
	private EmployeePrimaryInfo employeePrimaryInfo;
	private Object data;

}
