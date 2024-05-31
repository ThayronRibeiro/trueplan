package com.graco.trueplan.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String urlConnection;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	
	
	@Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        
        if(username != null) {
        	dataSourceBuilder.driverClassName(driverClassName);
        	dataSourceBuilder.url(urlConnection);
        	dataSourceBuilder.username(username);
        	dataSourceBuilder.password(password);
        }
        
        return dataSourceBuilder.build();
    }
}
