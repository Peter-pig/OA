package com.example.oa_user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commom.Entity.UserRoles;
import com.example.oa_user.mapper.userRolesMapper;
import com.example.oa_user.service.userRolesService;
import org.springframework.stereotype.Service;

@Service
public class userRolesServiceImpl extends ServiceImpl<userRolesMapper, UserRoles> implements userRolesService {
}
