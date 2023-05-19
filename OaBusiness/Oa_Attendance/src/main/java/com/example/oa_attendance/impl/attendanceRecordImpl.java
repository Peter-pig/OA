package com.example.oa_attendance.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commom.Entity.Attendancerecord;
import com.example.oa_attendance.mapper.attendanceRecordMapper;
import com.example.oa_attendance.service.attendanceRecordService;
import org.springframework.stereotype.Service;

@Service
public class attendanceRecordImpl extends ServiceImpl<attendanceRecordMapper, Attendancerecord> implements attendanceRecordService {
}
