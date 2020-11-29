package com.domgo.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domgo.entity.User;
import com.domgo.web.repository.UserMongoRepository;
import com.domgo.web.service.MongoService;

@Service
public class MongoServiceImpl implements MongoService{

	@Autowired
	private UserMongoRepository userMongoRepository;
	
	@Override
	public List<User> getUser(String name) {
		List<User> userList = userMongoRepository.findByNameLike(name);
		return userList;
	}
	
	@Override
	public void saveUser(User user) {
		userMongoRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		User tUser = userMongoRepository.findByName(user.getName());
		if(tUser != null) {
			tUser.setPwd(user.getPwd());
			tUser.setHeadImg(user.getHeadImg());
			tUser.setPhone(user.getPhone());
			tUser.setWechat(user.getWechat());
			userMongoRepository.save(tUser);
		}
	}

	@Override
	public void deleteUser(String name) {
		User tUser = userMongoRepository.findByName(name);
		if(tUser != null) {
			userMongoRepository.delete(tUser);
		}
	}

}
