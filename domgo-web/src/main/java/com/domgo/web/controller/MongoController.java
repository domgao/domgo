package com.domgo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domgo.entity.User;
import com.domgo.web.service.MongoService;

/**
 * MongoController example mongo op CRUD
 * @author Mr.Domgo
 * @Date 2020-11-29
 * @Since 1.0
 */
@RestController
@RequestMapping("mongo")
public class MongoController {

	@Autowired
	private MongoService mongoService;
	
	@GetMapping("getUser")
	public List<User> getUser(String name) {
		return mongoService.getUser(name);
	}
	
	@PostMapping("saveUser")
	public void saveUser(@RequestBody User user) {
		mongoService.saveUser(user);
	}
	
	@PostMapping("updateUser")
	public void updateUser(@RequestBody User user) {
		mongoService.updateUser(user);
	}
	
	@GetMapping("deleteUser")
	public void deleteUser(String name) {
		mongoService.deleteUser(name);
	}
	
}
