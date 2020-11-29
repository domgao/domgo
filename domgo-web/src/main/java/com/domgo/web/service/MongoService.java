package com.domgo.web.service;

import java.util.List;

import com.domgo.entity.User;

public interface MongoService {

	void saveUser(User user);

	List<User> getUser(String name);

	void updateUser(User user);

	void deleteUser(String name);

}
