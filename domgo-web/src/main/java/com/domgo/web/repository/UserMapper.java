package com.domgo.web.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.domgo.commons.annatation.ReadOnly;
import com.domgo.entity.User;

@Repository
public interface UserMapper {
	
	@ReadOnly
	@Select("select * from user where id = #{userId}")
	public User findById(@Param("userId") int userId);
	
	@Update("update user set username = #{name} where id = #{userId}")
	public void updateUser(@Param("name") String name, @Param("userId") int userId);
	
}
