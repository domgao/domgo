package com.domgo.entity.to;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class User {

	private Long id;
	private Long userId;
	private Long deptId;
	private String loginName;
	private String userName;
	private String userType;
	private String email;
	private String phonenumber;
	private String sex;
	private String avatar;
	private String password;
	
}
