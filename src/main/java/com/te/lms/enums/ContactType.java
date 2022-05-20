package com.te.lms.enums;

public enum ContactType {

PERSONAL("PERSONAL"),WHATSAPP("WHATSAPP"),HOME("HOME");
	
	private final String contactType;

	ContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactType() {
		return contactType;
	}
}
