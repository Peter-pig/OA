package com.example.commom.DTO;

import com.example.commom.Entity.Users;
import lombok.Data;

import java.io.Serializable;
@Data
public class UsersDto extends Users implements Serializable {
    private Integer roleId;
}
