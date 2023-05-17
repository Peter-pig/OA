package com.example.oa_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.oa_user.mapper")
public class OaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaUserApplication.class, args);
    }

}
