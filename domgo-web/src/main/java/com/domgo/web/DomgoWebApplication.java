package com.domgo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@MapperScan("com.domgo.web.repository")
@ComponentScan("com.domgo")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class DomgoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomgoWebApplication.class, args);
	}

}
