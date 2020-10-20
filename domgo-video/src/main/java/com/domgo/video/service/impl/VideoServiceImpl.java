package com.domgo.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domgo.entity.Video;
import com.domgo.video.dao.VideoMapper;
import com.domgo.video.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoMapper videoMapper;
	
	@Override
	public Video findById(int videoId) {
		return videoMapper.findById(videoId);
	}
}
