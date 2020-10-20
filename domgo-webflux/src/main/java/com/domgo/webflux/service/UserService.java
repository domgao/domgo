package com.domgo.webflux.service;

import com.domgo.webflux.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @description:用户服务接口
 * @author: Domgo
 * @create: 2020-04-19
 **/
public interface UserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    public Mono<User> insertUser(User user);

    /**
     * 根据昵称模糊查询用户
     * @param nickName
     * @return
     */
    Flux<User> queryUsers(String nickName);
}
