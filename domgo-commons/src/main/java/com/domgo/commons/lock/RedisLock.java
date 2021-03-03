package com.domgo.commons.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLock {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public String lockDemo() {
		String store = "store:001";
		String lockKey = "store:001:lock_key";
		String clientId = UUID.randomUUID().toString();
		try {
			Boolean lock = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
			if(!lock) {
				return "err";
			}
			int count = Integer.valueOf((String)redisTemplate.opsForValue().get(store));
			if(count > 0) {
				count--;
				redisTemplate.opsForValue().set(store, String.valueOf(count));
			}
			
		} finally {
			if(clientId.equals(redisTemplate.opsForValue().get(lockKey))) {
				redisTemplate.delete(lockKey);
			}
		}
		return "succ";
	}
	
}
