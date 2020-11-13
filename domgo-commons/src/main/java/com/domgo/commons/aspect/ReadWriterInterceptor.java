package com.domgo.commons.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.domgo.commons.annatation.ReadOnly;
import com.domgo.commons.db.DynamicDataSourceContextHolder;
import com.domgo.commons.enums.DataBaseMode;

@Order(0)
@Aspect
@Component
public class ReadWriterInterceptor {

	private static final Logger log = LoggerFactory.getLogger(ReadWriterInterceptor.class);
	
	@Around("@annotation(readOnly)")
	public Object interceptor(ProceedingJoinPoint point, ReadOnly readOnly) throws Throwable {
		try {
			String methodName = ((MethodSignature)point.getSignature()).getMethod().getName();
			DynamicDataSourceContextHolder.putDataSourceKey(DataBaseMode.READ);
			Object result = point.proceed();
			log.info("方法[{}]执行读库", methodName);
			return result;
		} finally {
			DynamicDataSourceContextHolder.removeMode();
		}
	}
	
}
