package com.te.lms.enums;

public enum MaritalStatus {

	SINGLE("SINGLE"), MARRIED("MARRIED"), WIDOWED("WIDOWED"), UNMARRIED("UNMARRIED");

	private final String maritalStatus;

	MaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMaritalStatus() {
		return maritalStatus;

	}
}
