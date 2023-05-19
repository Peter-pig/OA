package com.example.oa_attendance.client;

import com.example.commom.DTO.UsersDto;
import com.example.commom.Entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "oa-user")
public interface usersClient {
    @GetMapping("api/user/AttendanceUser/{email}")
    public UsersDto AttendanceUser(@PathVariable String email);
}
