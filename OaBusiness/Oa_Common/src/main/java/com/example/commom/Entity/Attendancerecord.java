package com.example.commom.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (Attendancerecord)实体类
 *
 * @author makejava
 * @since 2023-05-19 21:09:49
 */
@Data
@TableName("attendanceRecord")
@Table("attendacneRecord")
public class Attendancerecord implements Serializable {
    private static final long serialVersionUID = -17857608213481443L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer attendanceRuleId;

    private OffsetDateTime punchInTime;

    private OffsetDateTime punchOutTime;

    private Object status;

public Attendancerecord normal(Integer userId,Integer attendanceRuleId,OffsetDateTime punchInTime){
    Attendancerecord attendancerecord = new Attendancerecord();
    attendancerecord.setUserId(userId);
    attendancerecord.setAttendanceRuleId(attendanceRuleId);
    attendancerecord.setPunchInTime(punchInTime);
    attendancerecord.setStatus("上班打卡");
    return attendancerecord;
}

    public Attendancerecord workout(Integer userId,Integer attendanceRuleId,OffsetDateTime punchOutTime){
        Attendancerecord attendancerecord = new Attendancerecord();
        attendancerecord.setUserId(userId);
        attendancerecord.setAttendanceRuleId(attendanceRuleId);
        attendancerecord.setPunchOutTime(punchOutTime);
        attendancerecord.setStatus("下班打卡");
        return attendancerecord;
    }
    public Attendancerecord late(Integer userId,Integer attendanceRuleId,OffsetDateTime punchInTime){
        Attendancerecord attendancerecord = new Attendancerecord();
        attendancerecord.setUserId(userId);
        attendancerecord.setAttendanceRuleId(attendanceRuleId);
        attendancerecord.setPunchInTime(punchInTime);
        attendancerecord.setStatus("迟到打卡");
        return attendancerecord;
    }
    public Attendancerecord earlyout(Integer userId,Integer attendanceRuleId,OffsetDateTime punchOutTime){
        Attendancerecord attendancerecord = new Attendancerecord();
        attendancerecord.setUserId(userId);
        attendancerecord.setAttendanceRuleId(attendanceRuleId);
        attendancerecord.setPunchOutTime(punchOutTime);
        attendancerecord.setStatus("早退打卡");
        return attendancerecord;
    }
}

