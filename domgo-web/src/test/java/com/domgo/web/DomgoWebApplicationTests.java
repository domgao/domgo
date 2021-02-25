package com.domgo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import com.domgo.web.config.RabbitConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DomgoWebApplicationTests {

	@Autowired
	private ThreadPoolTaskExecutor cpuIntensiveThread;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Test
	public void testSend() {
		rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_EXCHANGE, "default.msg", "测试mq消息发送");
	}
	
	
	
	@Test
	public void contextLoads() {
		for (int i = 0; i < 100; i++) {
			final int l = i;
			cpuIntensiveThread.execute(()->{
				System.err.println(Thread.currentThread().getName() + "_" + l);
			});
		}
		
	}

}
