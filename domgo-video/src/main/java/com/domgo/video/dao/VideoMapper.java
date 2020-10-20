package com.domgo.video.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.domgo.entity.Video;

@Repository
public interface VideoMapper {

	@Select("select * from video where id = #{videoId}")
	public Video findById(@Param("videoId") int videoId);
	
}
