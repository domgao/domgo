package com.domgo.web.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

	@Value("${spring.datasource.type}")
	private Class<? extends DataSource> type;

	@Bean(name = "masterDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.write")
	public DataSource masterDataSource() {
		DataSource masterDataSource = DataSourceBuilder.create().type(type).build();
		log.info("======Write DataBase {}======", masterDataSource);
		return masterDataSource;
	}

	@Bean(name = "slaveDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.read")
	public DataSource slaveDataSource() {
		DataSource slaveDataSource = DataSourceBuilder.create().type(type).build();
		log.info("======Read DataBase {}======", slaveDataSource);
		return slaveDataSource;
	}

//	@Bean
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public ServletRegistrationBean druidServlet() {
//		ServletRegistrationBean srb = new ServletRegistrationBean<>();
//		srb.setServlet(new StatViewServlet());
//		srb.addUrlMappings("/druid/*");
//		srb.addInitParameter("allow", "localhost");
//		//srb.addInitParameter("deny", "/deny");
//		log.info(" druid console manager init : {} ", srb);
//		return srb;
//	}

	@Bean
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico, /druid/*");
		log.info(" druid filter register : {} ", filterRegistrationBean);
		return filterRegistrationBean;
	}

}
