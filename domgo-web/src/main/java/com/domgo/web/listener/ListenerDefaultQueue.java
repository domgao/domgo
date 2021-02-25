package com.domgo.web.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "default_queue")
public class ListenerDefaultQueue {

	@RabbitHandler
	public void listenerDefaultQueue(String msg, Message message) {
		long msgTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("msgTag="+msgTag);
        System.out.println("message="+message.toString());
        System.out.println("监听到消息：消息内容:"+message.getBody());
	}
	
}
