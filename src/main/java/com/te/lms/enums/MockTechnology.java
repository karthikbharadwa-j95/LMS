package com.te.lms.enums;

public enum MockTechnology {

REACT("REACT"), ANGULAR("ANGULAR"), JAVA_AND_SPRING_BOOT("JAVA_AND_SPRING_BOOT"), NODE_AND_EXPRESS_JS("NODE_AND_EXPRESS_JS");
	
	private final String mockTechnology;

	private MockTechnology(String mockTechnology) {
		this.mockTechnology = mockTechnology;
	}
	
	public String getMockTechnology() {
		return mockTechnology;
	} 
}
