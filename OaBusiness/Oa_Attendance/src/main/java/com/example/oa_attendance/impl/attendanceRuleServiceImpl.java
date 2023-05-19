package com.example.oa_attendance.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commom.Entity.Attendancerule;
import com.example.oa_attendance.mapper.attendanceRuleMapper;
import com.example.oa_attendance.service.attendanceRuleService;
import org.springframework.stereotype.Service;

@Service
public class attendanceRuleServiceImpl extends ServiceImpl<attendanceRuleMapper, Attendancerule> implements attendanceRuleService {
}
