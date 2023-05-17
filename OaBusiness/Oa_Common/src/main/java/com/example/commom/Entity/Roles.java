package com.example.commom.Entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * (Roles)实体类
 *
 * @author makejava
 * @since 2023-05-15 21:19:55
 */
@Data
@Table("roles")
public class Roles implements Serializable {
    private static final long serialVersionUID = -65792208775972487L;
    
    private Integer id;
    
    private String role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

