package com.itheima.pinda.teststarter1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyStarterProperties.class)
public class MyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public String testHello(MyStarterProperties props) {
        return "Hello, " + props.getName();
    }
}