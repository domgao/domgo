package com.domgo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@MapperScan("com.domgo.web.repository")
@EnableMongoRepositories(basePackages = "com.domgo.web.repository")
@SpringBootApplication(scanBasePackages = "com.domgo", exclude = DruidDataSourceAutoConfigure.class)
public class DomgoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomgoWebApplication.class, args);
	}

}
