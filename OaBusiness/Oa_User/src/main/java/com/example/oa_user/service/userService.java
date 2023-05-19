package com.example.oa_user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commom.DTO.UsersDto;
import com.example.commom.Entity.Roles;
import com.example.commom.Entity.UserRoles;
import com.example.commom.Entity.Users;

import java.util.List;

public interface userService extends IService<Users> {
    UserRoles compareRole(Integer id);

    List<Roles> getRoles();

    UsersDto AttendanceUser(String email);
}
