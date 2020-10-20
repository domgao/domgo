package com.domgo.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.domgo.webflux.domain.User;

import reactor.core.publisher.Flux;

/**
* @Description:用户接口持久层
* @Author: Domgo
* @Date: 2020/4/19
*/
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Long> {

    /**
     * 对用户昵称进行模糊查询
     * @param nickName
     * @return
     */
    public Flux<User> findByNickNameLike(String nickName);

}