package com.domgo.entity.nosql;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** 
* @Description: 用户信息实体类
* @Author: Domgo
* @Date: 2020/4/19 
*/ 
@Data
@Document("user")
public class User {
    @Id
    private String appId;
    private String nickName;
    private String avatarUrl;
    private Integer gender;
    private String qrCode;
    private String language;
    private String city;
    private String province;
    private String country;

}
