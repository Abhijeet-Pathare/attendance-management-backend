package com.codesoft.app.service;
import com.codesoft.app.dto.BatchReportDTO;
import com.codesoft.app.dto.StudentMonthlyReportDTO;
import com.codesoft.app.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    Attendance create(Attendance attendance);
    // Mark attendance

    Attendance update(Long id, Attendance attendance);

    Attendance getById(Long id);

    List<Attendance> list();

    List<Attendance> search(Long studentId, Long batchId, String date);

    void delete(Long id);

    StudentMonthlyReportDTO getStudentMonthlyReport(Long studentId, int month, int year);

    BatchReportDTO getBatchReport(Long batchId);
}
