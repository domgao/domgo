package com.domgo.web.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.domgo.commons.exception.ProcessException;
import com.domgo.commons.struct.Result;
import com.domgo.commons.util.DG;

/**
 * <p>ResponseControllerAdvice strengthen controller ability</p>
 * @author Mr.Domgo
 * @Date 2020-9-8
 * @Since 1.0
 */
@RestControllerAdvice(basePackages = "com.domgo")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object>{

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return !returnType.getParameterType().equals(Result.class);
	}

	@Override
	public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if(returnType.getGenericParameterType().equals(String.class)) {
			try {
				DG.json.serialize(new Result<>(data));
			} catch (Exception e) {
				throw new ProcessException("Controller层返回前JSON序列化错误");
			}
		}
		return new Result<>(data);
	}

}
