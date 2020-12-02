package com.domgo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RedisController example redis op
 * @author Mr.Domgo
 * @Date 2020-11-29
 * @Since 1.0
 */
@RestController
@RequestMapping("redis")
public class RedisController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping("setRedis")
	public void setRedis() {
	}
	
}
