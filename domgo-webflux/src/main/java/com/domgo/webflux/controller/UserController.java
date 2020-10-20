package com.domgo.webflux.controller;

import com.domgo.webflux.domain.User;
import com.domgo.webflux.domain.UserVo;
import com.domgo.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @description:用户接口
 * @author: Domgo
 * @create: 2020-04-19
 **/
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public Mono<UserVo> insertUser(@RequestBody User user){
       return userService.insertUser(user).map(u -> translate(u));
    }

    @GetMapping("/user/{nickName}")
    public Flux<UserVo> queryUsers(@PathVariable String nickName){
        return userService.queryUsers(nickName).map(u -> translate(u));
    }

    private UserVo translate(User u) {
        UserVo userVo = new UserVo();
        userVo.setAppId(u.getAppId());
        userVo.setNickName(u.getNickName());
        userVo.setAvatarUrl(u.getAvatarUrl());
        userVo.setGender(u.getGender());
        userVo.setQrCode(u.getQrCode());
        userVo.setLanguage(u.getLanguage());
        userVo.setCity(u.getCity());
        userVo.setProvince(u.getProvince());
        userVo.setCountry(u.getCountry());
        return userVo;
    }

}
