package com.example.oa_attendance.client;

import com.example.commom.DTO.HolidayDate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient( name = "holiday",url = "http://api.haoshenqi.top/holiday")
public interface HolidayClient {
    @GetMapping
    public List<HolidayDate> getByMonth(@RequestParam String date);
}
