package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropierties;
import com.fundamentos.springboot.fundamentos.bean.impl.MyBeanWithPropiertiesImpl;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;

    //If some value is wrong write trhow error
    @Value("${value.lastName}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    @Bean
    public MyBeanWithPropierties myBeanWithPropiertiesFuncion(){
        return new MyBeanWithPropiertiesImpl(name, apellido);
    }

}
