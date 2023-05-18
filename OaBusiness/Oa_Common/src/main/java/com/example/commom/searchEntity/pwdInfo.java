package com.example.commom.searchEntity;

import lombok.Data;

import java.io.Serializable;
@Data
public class pwdInfo implements Serializable {
   private String oldPwd;
   private String newPwd;
   private String confirmPwd;

}
