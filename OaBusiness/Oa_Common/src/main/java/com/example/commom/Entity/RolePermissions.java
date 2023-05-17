package com.example.commom.Entity;

import java.io.Serializable;

/**
 * (RolePermissions)实体类
 *
 * @author makejava
 * @since 2023-05-15 21:19:54
 */
public class RolePermissions implements Serializable {
    private static final long serialVersionUID = 419894196856830364L;
    
    private Integer roleId;
    
    private String permission;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}

