package com.te.lms.enums;

import javax.management.loading.PrivateClassLoader;

public enum BatchTechnology {

	REACT("REACT"), ANGULAR("ANGULAR"), JAVA_AND_SPRING_BOOT("JAVA_AND_SPRING_BOOT"), NODE_AND_EXPRESS_JS("NODE_AND_EXPRESS_JS");
	
	private final String batchTechnology;

	private BatchTechnology(String batchTechnology) {
		this.batchTechnology = batchTechnology;
	}
	
	public String getBatchTechnology() {
		return batchTechnology;
	} 
}
