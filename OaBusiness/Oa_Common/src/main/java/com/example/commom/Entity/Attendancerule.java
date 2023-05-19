package com.example.commom.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalTime;
import java.io.Serializable;

/**
 * (Attendancerule)实体类
 *
 * @author makejava
 * @since 2023-05-19 21:09:49
 */
@Data
@TableName("attendanceRule")
@Table("attendanceRule")
public class Attendancerule implements Serializable {
    private static final long serialVersionUID = -73311288919270355L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String name;
    
    private LocalTime startTime;
    
    private LocalTime endTime;

    private Integer belong;

    private LocalTime shiftDuration;

}

