package com.codesoft.app.controller;

import com.codesoft.app.entity.Attendance;
import com.codesoft.app.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.create(attendance);
    }

    @GetMapping("/daily")
    public List<Attendance> getDailyAttendance(@RequestParam Long batchId,
                                               @RequestParam String date) {
        return attendanceService.search(null, batchId, date);
    }

    @GetMapping("/monthly")
    public List<Attendance> getMonthlyAttendance(@RequestParam Long studentId,
                                                 @RequestParam int month) {
        return attendanceService.search(studentId, null, null);
    }
}
