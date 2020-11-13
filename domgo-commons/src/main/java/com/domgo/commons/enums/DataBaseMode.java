package com.domgo.commons.enums;

public enum DataBaseMode {

	READ("read"),
	
	WRITE("write");
	
	private String mode;

	private DataBaseMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
