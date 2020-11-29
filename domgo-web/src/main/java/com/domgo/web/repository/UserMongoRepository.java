package com.domgo.web.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.domgo.entity.User;

@Repository
public interface UserMongoRepository extends MongoRepository<User, Long>{

	List<User> findByNameLike(String name);
	
	User findByName(String name);
	
}
