package com.domgo.order.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.domgo.entity.Video;
import com.domgo.entity.VideoOrder;

@RestController
@RequestMapping("api/v1/video_order")
public class OrderController {

	@Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/save")
    public Object save(int videoId){

        Video video = restTemplate.getForObject("http://localhost:8002/api/v1/video/find_by_id?videoId="+videoId, Video.class);

        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setVideoId(video.getId());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        return videoOrder;

    }

	
}
