package com.domgo.web.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDefaultQueue {

	@RabbitListener(queues = "default_queue")
	@RabbitHandler
	public void listenerDefaultQueue(String msg, Message message) {
		long msgTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("msgTag="+msgTag);
        System.out.println("message="+message.toString());
        System.out.println("listenerDefaultQueue监听到消息：消息内容:"+message.getBody());
	}
	
	@RabbitListener(queues = "dead_queue")
	@RabbitHandler
	public void listenerDeadQueue(String msg, Message message) {
		long msgTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("msgTag="+msgTag);
        System.out.println("message="+message.toString());
        System.out.println("listenerDeadQueue监听到消息：消息内容:"+message.getBody());
	}
	
}
