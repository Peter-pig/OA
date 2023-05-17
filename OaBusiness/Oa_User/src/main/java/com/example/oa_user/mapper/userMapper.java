package com.example.oa_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commom.Entity.Roles;
import com.example.commom.Entity.UserRoles;
import com.example.commom.Entity.Users;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface userMapper extends BaseMapper<Users> {
    @Select("select * from `user_roles` where `user_id` = #{id}")
    UserRoles compareRole(Integer id);

    @Select("select * from `roles`")
    List<Roles> getRoles();
}
