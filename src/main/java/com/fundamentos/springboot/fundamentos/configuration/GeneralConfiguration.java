package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropierties;
import com.fundamentos.springboot.fundamentos.bean.impl.MyBeanWithPropiertiesImpl;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.propierties")
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;

    //If some value is wrong write trhow error
    @Value("${value.lastName}")
    private String apellido;

    @Value("${value.random}")
    private String random;


    //Values fron new file propierties
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.username}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;


    @Bean
    public MyBeanWithPropierties myBeanWithPropiertiesFuncion(){
        return new MyBeanWithPropiertiesImpl(name, apellido);
    }

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName(driver);
        builder.url(jdbcUrl);
        builder.username(userName);
        builder.password(password);
        return builder.build();
    }

}
