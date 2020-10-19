package com.domgo.commons.struct;

import com.domgo.commons.enums.ResultStatus;

public class Result<T> {

	private String code;
	
	private String message;
	
	private T data;
	
	public Result() {}

	public Result(T data) {
		this(ResultStatus.SUCCESS, data);
	}

	public Result(ResultStatus resultStatus, T data) {
		super();
		this.code = resultStatus.getCode();
		this.message = resultStatus.getMessage();
		this.data = data;
	}

	public Result(String code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
    
}
