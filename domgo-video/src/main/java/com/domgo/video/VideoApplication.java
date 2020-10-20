package com.domgo.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.domgo.video.dao")
public class VideoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(VideoApplication.class, args);
	}

}
