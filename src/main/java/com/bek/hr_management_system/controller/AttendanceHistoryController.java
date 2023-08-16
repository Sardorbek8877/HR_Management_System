package com.bek.hr_management_system.controller;

import com.bek.hr_management_system.payload.ApiResponse;
import com.bek.hr_management_system.payload.AttendanceHistoryDto;
import com.bek.hr_management_system.service.AttendanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AttendanceHistoryController {

    @Autowired
    AttendanceHistoryService attendanceHistoryService;

    @PostMapping("/enterOrExit")
    public HttpEntity<?> enterOrExit(@RequestBody AttendanceHistoryDto attendanceHistoryDto){
        ApiResponse apiResponse = attendanceHistoryService.enterOrExit(attendanceHistoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
}
