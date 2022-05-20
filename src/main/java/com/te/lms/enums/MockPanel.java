package com.te.lms.enums;

public enum MockPanel {

	SHRUTHI("SHRUTHI"), AGASTHYA("AGASTHYA"), BINDU("BINDU"), KARIM("KARIM");

	private final String mockPanel;

	MockPanel(String mockPanel) {
		this.mockPanel = mockPanel;
	}

	public String getMockPanel() {
		return mockPanel;
	}
}
