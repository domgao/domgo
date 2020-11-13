package com.domgo.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domgo.entity.User;
import com.domgo.web.repository.UserMapper;

@Service
public class UserServiceImpl {

	@Autowired
	private UserMapper userMapper;
	
	public void testWriteAndRead(int userId, String username) {
		User user = userMapper.findById(userId);
		if(user != null) {
			userMapper.updateUser(username, userId);
		}
	}
	
}
