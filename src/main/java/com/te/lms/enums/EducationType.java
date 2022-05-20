package com.te.lms.enums;

public enum EducationType {

	SSLC("SSLC"), PUC("PUC"), GRADUATE("GRADUATE"), POST_GRADUATE("POST_GRADUATE");

	private final String educationType;

	EducationType(String educationType) {
		this.educationType = educationType;
	}

	public String getEducationType() {
		return educationType;

	}
}
