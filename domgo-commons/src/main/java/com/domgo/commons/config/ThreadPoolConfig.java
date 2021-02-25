package com.domgo.commons.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

	private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfig.class);
	
	private static final int CPU_CORE_NUM = Runtime.getRuntime().availableProcessors();
	
	@Bean("cpuIntensiveThread")
	public ThreadPoolTaskExecutor cpuIntensiveThread() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(CPU_CORE_NUM + 1);
		executor.setMaxPoolSize(CPU_CORE_NUM + 1);
		executor.setQueueCapacity((CPU_CORE_NUM + 1) * 10);
		executor.setThreadNamePrefix("CpuIntensiveThread_");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setKeepAliveSeconds(60);
		executor.initialize();
		log.info("cpuIntensiveThread is created init finished");
	
		return executor;
	}
	
	@Bean("ioIntensiveThread")
	public ThreadPoolTaskExecutor ioIntensiveThread() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(CPU_CORE_NUM + 2);
		executor.setMaxPoolSize(CPU_CORE_NUM + 2);
		executor.setQueueCapacity((CPU_CORE_NUM + 2) * 10);
		executor.setThreadNamePrefix("IoIntensiveThread_");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setKeepAliveSeconds(60);
		executor.initialize();
		log.info("ioIntensiveThread is created init finished");
		return executor;
	}
	
}
