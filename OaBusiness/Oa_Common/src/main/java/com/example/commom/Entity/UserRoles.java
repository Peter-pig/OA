package com.example.commom.Entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * (UserRoles)实体类
 *
 * @author makejava
 * @since 2023-05-15 21:19:55
 */
@Data
@Table("user_roles")
public class UserRoles implements Serializable {
    private static final long serialVersionUID = 397745958829451278L;
    
    private Integer userId;
    
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}

