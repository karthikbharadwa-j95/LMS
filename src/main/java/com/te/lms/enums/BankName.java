package com.te.lms.enums;

public enum BankName {

		SBI("SBI"), KBL("KBL"), ICICI("ICICI"), HDFC("HDFC"),CANARA_BANK("CANARA_BANK"),CITY_BANK("CITY_BANK"),
		BOB("BOB"),BOI("BOI"),PNB("PNB"),APEX("APEX"),KOTAK("KOTAK"),IOB("IOB"),IDBI("IDBI"),CORPORATION_BANK("CORPORATION_BANK"),
		SYNDICATE_BANK("SYNDICATE_BANK"),UBI("UBI"),UCO("UCO");

		private final String bankName;

		BankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBankName() {
			return bankName;
		}
}
