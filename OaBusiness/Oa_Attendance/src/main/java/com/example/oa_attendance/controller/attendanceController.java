package com.example.oa_attendance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.commom.DTO.HolidayDate;
import com.example.commom.DTO.UsersDto;
import com.example.commom.Entity.Attendancerecord;
import com.example.commom.Entity.Attendancerule;

import com.example.commom.R.R;
import com.example.commom.Utils.JwtUtils;
import com.example.oa_attendance.client.HolidayClient;
import com.example.oa_attendance.client.usersClient;
import com.example.oa_attendance.service.attendanceRecordService;
import com.example.oa_attendance.service.attendanceRuleService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("api/attendance")
public class attendanceController {
    @Resource
    attendanceRuleService attendanceRuleService;

    @Resource
    usersClient usersClient;

    @Resource
    attendanceRecordService attendanceRecordService;

    @Resource
    HolidayClient holidayClient;


    //only person who like me can write out this flavour's code , im great 🧑‍💻
    @PostMapping("punchIn")
    public R punchIn(@RequestBody Attendancerecord attendanceRecord, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.replace("Bearer ", "");
        Claims claims = JwtUtils.decodeToken(token);
        String subject = claims.getSubject();

        UsersDto users = usersClient.AttendanceUser(subject);
        LambdaQueryWrapper<Attendancerule> rule = new LambdaQueryWrapper<>();
        rule.eq(Attendancerule::getBelong, users.getRoleId());
        Attendancerule one = attendanceRuleService.getOne(rule);

        String format = attendanceRecord.getPunchInTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<HolidayDate> byMonth = holidayClient.getByMonth(format);
        LocalTime punchIn = LocalTime.parse(attendanceRecord.getPunchInTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Attendancerecord attendancerecord = new Attendancerecord();

        switch (byMonth.get(0).getStatus()) {

            case 0:
                //正常上班
                LocalTime startTime = one.getStartTime().minusMinutes(30);
                LambdaQueryWrapper<Attendancerecord> attendancerecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
                attendancerecordLambdaQueryWrapper.eq(Attendancerecord::getUserId, users.getId())
                        .ge(Attendancerecord::getPunchInTime, attendanceRecord.getPunchInTime().truncatedTo(ChronoUnit.DAYS))
                        .lt(Attendancerecord::getPunchInTime, attendanceRecord.getPunchInTime().plusDays(1).truncatedTo(ChronoUnit.DAYS));

                Attendancerecord todayRecords = attendanceRecordService.getOne(attendancerecordLambdaQueryWrapper);
                if (punchIn.isBefore(startTime)) {
                    return R.error(500, "不在打卡时间范围内");
                }
                else if (punchIn.isAfter(startTime) && punchIn.isBefore(one.getStartTime())) {
                    if (ObjectUtils.isNotNull(todayRecords)&&todayRecords.getPunchInTime()!= null){
                        return R.error("不可重复打卡");
                    }else {
                        attendancerecord.normal(users.getId(), one.getId(), attendanceRecord.getPunchInTime());
                        attendanceRecordService.save(attendancerecord);
                        return R.success("上班打卡");
                    }
                }
                else if (punchIn.isAfter(one.getStartTime())) {
                    if (ObjectUtils.isNotNull(todayRecords)&&todayRecords.getPunchInTime()!= null){
                        return R.error("已迟到");
                    }else {
                        attendancerecord.late(users.getId(), one.getId(), attendanceRecord.getPunchInTime());
                        attendanceRecordService.save(attendancerecord);
                        return R.success("迟到打卡");
                    }
                }
                else if (punchIn.isBefore(one.getEndTime()) && ObjectUtils.isNotNull(todayRecords)) {
                    if (ObjectUtils.isNotNull(todayRecords)&&todayRecords.getPunchOutTime()!= null){
                        return R.error("已早退");
                    }else {
                        attendancerecord.earlyout(users.getId(), one.getId(), attendanceRecord.getPunchInTime());
                        attendanceRecordService.save(attendancerecord);
                        return R.success("早退打卡");
                    }
                }
                else if(punchIn.isAfter(one.getEndTime()) && ObjectUtils.isNotNull(todayRecords)) {
                    attendancerecord.workout(users.getId(), one.getId(), attendanceRecord.getPunchInTime());
                    attendanceRecordService.saveOrUpdate(attendancerecord);
                    return R.success("下班打卡");
                }else {
                    return R.error("旷工");
                }
            case 1:
                return R.error("今天休息");
            case 2:
                return R.error("今天补班");
            case 3:
                return R.error("法定节假日");
            default:
                return R.error("系统出错");
        }

    }
}
