package com.te.lms.enums;

public enum MockRating {

	EXCELLENT("EXCELLENT"), GOOD("GOOD"), ABOVE_AVERAGE("ABOVE_AVERAGE"), BELOW_AVERAGE("BELOW_AVERAGE");

	private final String mockRating;

	MockRating(String mockRating) {
		this.mockRating = mockRating;
	}

	public String getMockRating() {
		return mockRating;
	}
}
