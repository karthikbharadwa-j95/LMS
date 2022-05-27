package com.te.lms.dto.mentor;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class MentorChangePassword {

	private String mentorId;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private String username;
}
