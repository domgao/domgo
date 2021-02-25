package com.domgo.web.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

	public static final String DEFAULT_EXCHANGE = "default_exchange";
	
	public static final String DEFAULT_QUEUE = "default_queue";
	
	@Bean
	public Exchange defaultExchange() {
		return new TopicExchange(DEFAULT_EXCHANGE, true, false);
	}
	
	@Bean
	public Queue defaultQueue() {
		return new Queue(DEFAULT_QUEUE, true, false, false, null);
	}
	
	@Bean
	public Binding defaultBinding() {
		return new Binding(DEFAULT_QUEUE, Binding.DestinationType.QUEUE, DEFAULT_EXCHANGE, "default.#", null);
	}
	
}
