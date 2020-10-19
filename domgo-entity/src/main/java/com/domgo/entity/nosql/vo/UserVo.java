package com.domgo.entity.nosql.vo;

import lombok.Data;

/**
 * @description:用户信息前端展示包装类
 * @author: Domgo
 * @create: 2020-04-19
 **/
@Data
public class UserVo {
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
