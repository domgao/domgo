package com.domgo.webflux.service.impl;

import com.domgo.webflux.domain.User;
import com.domgo.webflux.repository.UserRepository;
import com.domgo.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @description:用户接口服务实现类
 * @author: Domgo
 * @create: 2020-04-19
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Flux<User> queryUsers(String nickName) {
        return userRepository.findByNickNameLike(nickName);
    }

}
