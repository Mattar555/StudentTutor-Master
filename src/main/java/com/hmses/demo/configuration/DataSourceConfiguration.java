package com.hmses.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    //TODO: Pass in port and database name as seperate externalized variables and construct url accordingly (LOW Prior)
    private final static int MY_SQL_PORT = 3306;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.password(password);
        dataSourceBuilder.username(username);
        dataSourceBuilder.url(url);
        return dataSourceBuilder.build();
    }
}
