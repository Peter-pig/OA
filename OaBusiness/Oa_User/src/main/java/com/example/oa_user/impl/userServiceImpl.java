package com.example.oa_user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commom.DTO.UsersDto;
import com.example.commom.Entity.Roles;
import com.example.commom.Entity.UserRoles;
import com.example.commom.Entity.Users;
import com.example.oa_user.mapper.userMapper;
import com.example.oa_user.service.userService;
import com.mybatisflex.core.BaseMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl extends ServiceImpl<userMapper, Users> implements userService {
   @Resource
    userMapper userMapper;

   @Override
    public UserRoles compareRole(Integer id) {
        return userMapper.compareRole(id);
    }

    @Override
    public List<Roles> getRoles() {
        return userMapper.getRoles();
    }

    @Override
    public UsersDto AttendanceUser(String email) {
        return userMapper.AttendanceUser(email);
    }


}
