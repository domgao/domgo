package com.domgo.web.config;

import java.util.HashMap;
import java.util.Map;

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
	
	public static final String DEAD_EXCHANGE = "dead_exchange";
	
	public static final String DEAD_QUEUE = "dead_queue";
	
	public static final String DEAD_ROUTER_KEY = "dead_router_key";
	
	public static final String UNDEAD_EXCHANGE = "undead_exchange";
	
	public static final String UNDEAD_QUEUE = "undead_queue";
	
	public static final String UNDEAD_ROUTER_KEY = "undead_router_key";
	
	//commons exchange and queue
	
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
	
	//common queue time-to-live into dead exchange and queue by routerKey delay queue
	
	@Bean
	public Exchange deadExchange() {
		return new TopicExchange(DEAD_EXCHANGE, true, false);
	}
	
	@Bean
	public Queue deadQueue() {
		return new Queue(DEAD_QUEUE, true, false, false, null);
	}
	
	@Bean
	public Binding deadBinding() {
		return new Binding(DEAD_QUEUE, Binding.DestinationType.QUEUE, DEAD_EXCHANGE, DEAD_ROUTER_KEY, null);
	}
	
	@Bean
	public Exchange undeadExchange() {
		return new TopicExchange(UNDEAD_EXCHANGE, true, false);
	}
	
	@Bean
	public Queue undeadQueue() {
		Map<String,Object> args = new HashMap<String,Object>(3);
		//消息过期后，进入到死信交换机
		args.put("x-dead-letter-exchange", DEAD_EXCHANGE);
		//消息过期后，进入到死信交换机的路由key
		args.put("x-dead-letter-routing-key", DEAD_ROUTER_KEY);
		//过期时间，单位毫秒
		args.put("x-message-ttl", 10000);
		return new Queue(UNDEAD_QUEUE, true, false, false, args);
	}
	
	@Bean
	public Binding undeadBinding() {
		return new Binding(UNDEAD_QUEUE, Binding.DestinationType.QUEUE, UNDEAD_EXCHANGE, UNDEAD_ROUTER_KEY, null);
	}
	
}
