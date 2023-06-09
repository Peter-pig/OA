package com.example.oa_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.commom.DTO.UsersDto;
import com.example.commom.R.R;
import com.example.commom.Utils.BcryptUtil;
import com.example.commom.Entity.Roles;
import com.example.commom.Entity.UserRoles;
import com.example.commom.Entity.Users;
import com.example.commom.Utils.JwtUtils;
import com.example.commom.searchEntity.pwdInfo;
import com.example.commom.searchEntity.userSearch;
import com.example.oa_user.mapper.flexMapper;
import com.example.oa_user.service.userRolesService;
import com.example.oa_user.service.userService;

import com.mybatisflex.core.paginate.Page;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.example.commom.src.main.java.com.example.commom.Entity.table.Tables.USERS;
import static com.example.commom.src.main.java.com.example.commom.Entity.table.Tables.USER_ROLES;


@RestController
@RequestMapping("api/user")
public class userController {
    @Resource
    userService userService;
    @Resource
    userRolesService userRolesService;
    @Resource
    flexMapper flexMapper;


    @PostMapping("signIn")
    public R signIn(@RequestBody Users users) {
        String email = users.getEmail();
        LambdaQueryWrapper<Users> usersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        usersLambdaQueryWrapper.eq(Users::getEmail, email);
        Users one = userService.getOne(usersLambdaQueryWrapper);
        if (ObjectUtils.isNull(one)) {
            return R.error(500, "用户不存在");
        }
        if (BcryptUtil.match(users.getPassword(), one.getPassword())) {
            UserRoles userRoles = userService.compareRole(one.getId());
            String token = JwtUtils.generateToken(users.getEmail());
            if (userRoles.getRoleId() == 1) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("url", "/Admin");
                return R.success(data);
            } else {
                HashMap<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("url", "/normalUser");
                return R.success(data);
            }

        }
        return R.error(500, "密码错误");
    }

    @PostMapping("/")
    public R users(@RequestBody userSearch userSearch) {
        try {

            com.mybatisflex.core.query.QueryWrapper on = com.mybatisflex.core.query.QueryWrapper.create()
                    .select(USERS.ALL_COLUMNS)
                    .select(USER_ROLES.ROLE_ID)
                    .from(USERS)
                    .leftJoin(USER_ROLES)
                    .on(USERS.ID.eq(USER_ROLES.USER_ID));
            Page<UsersDto> paginate = flexMapper.paginate(new Page<UsersDto>(userSearch.getPage(), userSearch.getSize()), on);
            HashMap<String, Object> data = new HashMap<>();
            data.put("data", paginate);
            return R.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(500, "查询失败");
        }
    }

    @PostMapping("register")
    public R register(@RequestBody Users users) {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("name", users.getName())
                .eq("email", users.getEmail());
        Users one = userService.getOne(usersQueryWrapper);
        if (ObjectUtils.isNotNull(one)) {
            return R.error(500, "当前用户已存在");
        }
        users.setPassword(BcryptUtil.encrypt(users.getPassword()));
        userService.save(users);
        HashMap<String, Object> data = new HashMap<>();
        data.put("url", "/");
        return R.success(data);
    }

    @PutMapping("{id}")
    public R edit(@PathVariable String id, @RequestBody UsersDto users) {
        try {
            users.setId(Integer.valueOf(id));
            UserRoles userRoles = new UserRoles();
            userRoles.setUserId(users.getId());
            userRoles.setRoleId(users.getRoleId());
            userRolesService.saveOrUpdate(userRoles);
            userService.updateById(users);
            return R.success();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return R.error(500, "修改失败");
        }
    }

    @GetMapping("roles")
    public R roles() {
        try {
            List<Roles> rolesList = userService.getRoles();
            return R.success(rolesList);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(500, "查询失败");
        }
    }

    @GetMapping("/userInfo")
    public R userInfo(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.replace("Bearer ", "");
        Claims claims = JwtUtils.decodeToken(token);
        String subject = claims.getSubject();
        try {
            LambdaQueryWrapper<Users> usersLambdaQueryWrapper = new LambdaQueryWrapper<>();
            usersLambdaQueryWrapper.eq(Users::getEmail, subject);
            Users one = userService.getOne(usersLambdaQueryWrapper);
            HashMap<String, Object> data = new HashMap<>();
            data.put("data", one);
            return R.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(500, "查询失败");
        }

    }

    @PutMapping("/userUpdata")
    public R userUpdata(@RequestBody Users users) {
        try {
            userService.updateById(users);
            return R.success();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(500, "修改失败");
        }
    }

    @PutMapping("/changePassword")
    public R updatePassword(@RequestParam String email,@RequestBody pwdInfo pwdInfo ) {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("email", email);
        Users one = userService.getOne(usersQueryWrapper);
        if (!BcryptUtil.match( pwdInfo.getOldPwd(),one.getPassword())) {
            return R.error(500, "原密码输入错误");
        }
        String encrypt = BcryptUtil.encrypt(pwdInfo.getConfirmPwd());
        one.setPassword(encrypt);
        userService.updateById(one);
        return R.success();
    }

    @GetMapping("AttendanceUser/{email}")
    public UsersDto AttendanceUser(@PathVariable String email){
    return userService.AttendanceUser(email);
    }
}
