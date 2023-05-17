package com.example.commom.Entity;

import com.baomidou.mybatisplus.annotation.*;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2023-05-15 21:19:55
 */
@Data
@Table("users")
public class Users implements Serializable {
    private static final long serialVersionUID = -59939183115354199L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String position;

    private String contact;

}

