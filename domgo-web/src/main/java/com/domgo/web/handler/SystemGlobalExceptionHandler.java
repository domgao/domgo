package com.domgo.web.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.domgo.commons.enums.ResultStatus;
import com.domgo.commons.exception.ProcessException;
import com.domgo.commons.struct.Result;

/**
 * <p>The GlobalExceptionHandler class process internal system exception</p>
 * @author Mr.Domgo
 * @Date 2020-9-8
 * @Since 1.0
 */
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class SystemGlobalExceptionHandler {
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(SystemGlobalExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
		return new Result<String>(ResultStatus.ARGS_VALID_FAILED, objectError.getDefaultMessage());
	}
	
	@ExceptionHandler(ProcessException.class)
	public Result<String> processException(ProcessException pe){
		if(StringUtils.isNotBlank(pe.getErrorCode())) {
			return new Result<String>(pe.getErrorCode(), pe.getMessage(), null);
		}
		return new Result<String>(ResultStatus.FAILED.getCode(), pe.getMessage(), null);
	}
	
}
