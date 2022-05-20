package com.te.lms.dto.security;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class User {

	@Id
	private String userId;
	private String userRoles;

}
