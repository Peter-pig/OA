package com.example.commom.searchEntity;

import com.example.commom.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userSearch extends Users implements Serializable {
    private Integer page;
    private Integer size;
}
