package com.domgo.commons.exception;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;

public class ProcessException extends RuntimeException {

	private static final long serialVersionUID = 8587648330257009533L;
	private String errorCode;
	private Object[] args;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public ProcessException() {}
	
	public ProcessException(String errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
    }

	public ProcessException(String message, Object...args) {
		super(convertMessage(message, new Object[0]));
	}
	
	public ProcessException(String errorCode, String message, Object...args) {
		super(convertMessage(message, args));
		this.errorCode = errorCode;
		this.args = args;
	}
	
	private static String convertMessage(String message, Object...args) {
		if(message == null) {
			return StringUtils.EMPTY;
		}
		if(args != null && args.length > 0) {
			return MessageFormat.format(message, args);
		}
		return message;
	}
	
	
}
