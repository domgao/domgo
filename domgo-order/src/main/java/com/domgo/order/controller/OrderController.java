package com.domgo.order.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
	
	@Autowired
	private DiscoveryClient discoveryClient;

    @RequestMapping("/save")
    public Object save(int videoId){

        //Video video = restTemplate.getForObject("http://localhost:8002/api/v1/video/find_by_id?videoId="+videoId, Video.class);
    	
    	//List<ServiceInstance> list = discoveryClient.getInstances("domgo-video-service");
    	//ServiceInstance serviceInstance = list.get(0);
    	//Video video = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/api/v1/video/find_by_id?videoId="+videoId, Video.class);
    	
    	Video video = restTemplate.getForObject("http://domgo-video-service/api/v1/video/find_by_id?videoId="+videoId, Video.class);
    	
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setVideoId(video.getId());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setServerInfo(video.getServerInfo());
        return videoOrder;

    }

	
}
