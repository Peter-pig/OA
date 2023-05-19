package com.example.oa_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commom.DTO.UsersDto;
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
@Select("SELECT `users`.`id`, `users`.`name`, `users`.`email`, `users`.`password`, `users`.`position`, `users`.`contact`, `user_roles`.`role_id` FROM `users` LEFT JOIN `user_roles` ON `users`.`id` = `user_roles`.`user_id` WHERE `users`.`email` = #{email}")
    UsersDto AttendanceUser(String email);
}
