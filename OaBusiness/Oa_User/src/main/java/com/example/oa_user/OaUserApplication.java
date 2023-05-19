package com.example.oa_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@MapperScan("com.example.oa_user.mapper")
@EnableDiscoveryClient
public class OaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaUserApplication.class, args);
    }

}
