package com.domgo.commons.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Record method executed time
 * @author Mr.Domgo
 * @Date 2020-11-11
 * @Since 1.0
 */
@Aspect
@Component
public class MethodExecuteTimeLogAspect {

	private static final Logger log = LoggerFactory.getLogger(MethodExecuteTimeLogAspect.class);
	
	@Pointcut("@annotation(com.domgo.commons.annatation.MethodExecuteTimeLog)")
	public void methodPointcut() {}
	
	@Around("methodPointcut()")
	public Object interceptor(ProceedingJoinPoint point) throws Throwable {
		if(!log.isDebugEnabled()) {
			return point.proceed();
		}
		String className = point.getSignature().getDeclaringTypeName();
		String methodName = ((MethodSignature)point.getSignature()).getMethod().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = point.proceed();
		stopWatch.stop();
		log.debug("类[{}]方法[{}]执行耗时[{}]ms.", className, methodName, stopWatch.getTotalTimeMillis());
		return result;
	}
	
}
