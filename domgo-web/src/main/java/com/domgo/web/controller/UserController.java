package com.domgo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domgo.web.service.impl.UserServiceImpl;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("save")
	public void insertUser(int userId, String name) {
		userServiceImpl.testWriteAndRead(userId, name);
	}
	
}
