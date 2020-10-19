package com.domgo.commons.enums;

public enum ResultStatus {

	SUCCESS("1000", "success"),
	FAILED("1001", "failed"),
    ARGS_VALID_FAILED("1002", "参数校验失败"),
    FILE_CERATE_FAILED("1003", "文件创建失败");
	
	/**
	 * <p>业务系统异常码</p>
	 */
	private String code;
	
	/**
	 * <p>业务异常信息描述</p>
	 */
	private String message;

	private ResultStatus(String code, String message) {
		this.code = code;
		this.message = message;
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

}
