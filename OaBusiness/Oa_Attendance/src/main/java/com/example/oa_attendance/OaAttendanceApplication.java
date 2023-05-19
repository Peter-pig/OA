package com.example.oa_attendance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@MapperScan("com.example.oa_attendance.mapper")
@EnableFeignClients
public class OaAttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaAttendanceApplication.class, args);
    }

}
