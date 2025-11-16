package com.codesoft.app.service.impl;

import com.codesoft.app.dto.BatchReportDTO;
import com.codesoft.app.dto.StudentMonthlyReportDTO;
import com.codesoft.app.entity.Attendance;
import com.codesoft.app.repository.AttendanceRepository;
import com.codesoft.app.repository.StudentRepository;
import com.codesoft.app.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Attendance create(Attendance attendance) {
        // check if student exists and is ACTIVE
        if (!studentRepository.existsById(attendance.getStudentId())) {
            throw new RuntimeException("Student not found with id: " + attendance.getStudentId());
        }

        // check for duplicate attendance for same student, batch, date
        List<Attendance> existing = attendanceRepository
                .findByStudentIdAndBatchIdAndDate(attendance.getStudentId(), attendance.getBatchId(), attendance.getDate());
        if (!existing.isEmpty()) {
            throw new RuntimeException("Attendance already marked for this student on this date");
        }

        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance update(Long id, Attendance attendance) {
        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        existing.setStatus(attendance.getStatus());
        existing.setRemark(attendance.getRemark());
        return attendanceRepository.save(existing);
    }

    @Override
    public Attendance getById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
    }

    @Override
    public List<Attendance> list() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> search(Long studentId, Long batchId, String date) {
//        return attendanceRepository.findByStudentIdAndBatchIdAndDate(studentId, batchId, date);
        return attendanceRepository.findByBatchIdAndDate(batchId,date);
    }


    @Override
    public void delete(Long id) {
        Attendance existing = getById(id);
        attendanceRepository.delete(existing);
    }


    @Override
    public StudentMonthlyReportDTO getStudentMonthlyReport(Long studentId, int month, int year) {

        String yearMonth = String.format("%04d-%02d", year, month);

        List<Attendance> attendances =
                attendanceRepository.findByStudentIdAndDateStartingWith(studentId, yearMonth);

        int present = 0;
        int absent = 0;
        int late = 0;

        for (Attendance att : attendances) {
            switch (att.getStatus()) {
                case "PRESENT": present++; break;
                case "ABSENT":  absent++; break;
                case "LATE":    late++; break;
            }
        }

        int total = present + absent + late;

        double percentage = (total == 0)
                ? 0.0
                : (present * 100.0 / total);

        StudentMonthlyReportDTO dto = new StudentMonthlyReportDTO();
        dto.setStudentId(studentId);
        dto.setTotalPresent(present);
        dto.setTotalAbsent(absent);
        dto.setTotalLate(late);
        dto.setAttendancePercentage(percentage);

        return dto;
    }

    @Override
    public BatchReportDTO getBatchReport(Long batchId) {

        List<Attendance> attendances = attendanceRepository.findByBatchId(batchId);

        int present = 0;
        int absent = 0;
        int late = 0;

        for (Attendance att : attendances) {
            switch (att.getStatus()) {
                case "PRESENT": present++; break;
                case "ABSENT":  absent++; break;
                case "LATE":    late++; break;
            }
        }

        BatchReportDTO dto = new BatchReportDTO();
        dto.setBatchId(batchId);
        dto.setTotalPresent(present);
        dto.setTotalAbsent(absent);
        dto.setTotalLate(late);

        return dto;
    }
}
