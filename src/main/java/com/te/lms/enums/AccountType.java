package com.te.lms.enums;

public enum AccountType {

	SAVINGS_BANK("SAVINGS_BANK"), CURRENT_ACCOUNT("CURRENT_ACCOUNT"), SALARY_ACCOUNT("SALARY_ACCOUNT");

	private final String accountType;


	AccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;

	}
}
