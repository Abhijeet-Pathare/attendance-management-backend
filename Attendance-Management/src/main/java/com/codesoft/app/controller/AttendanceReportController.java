package com.codesoft.app.controller;

import com.codesoft.app.dto.StudentMonthlyReportDTO;
import com.codesoft.app.dto.BatchReportDTO;
import com.codesoft.app.service.AttendanceService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance/report")
public class AttendanceReportController {

    private final AttendanceService attendanceService;

    public AttendanceReportController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/student/{id}")
    public StudentMonthlyReportDTO getStudentReport(
            @PathVariable Long id,
            @RequestParam int month,
            @RequestParam int year) {

        return attendanceService.getStudentMonthlyReport(id, month, year);
    }

    @GetMapping("/batch/{batchId}")
    public BatchReportDTO getBatchReport(@PathVariable Long batchId) {
        return attendanceService.getBatchReport(batchId);
    }
}
