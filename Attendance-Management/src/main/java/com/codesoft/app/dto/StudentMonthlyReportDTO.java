package com.codesoft.app.dto;

public class StudentMonthlyReportDTO {

    private Long studentId;
    private int totalPresent;
    private int totalAbsent;
    private int totalLate;
    private double attendancePercentage;

    public StudentMonthlyReportDTO() {}

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(int totalPresent) {
        this.totalPresent = totalPresent;
    }

    public int getTotalAbsent() {
        return totalAbsent;
    }

    public void setTotalAbsent(int totalAbsent) {
        this.totalAbsent = totalAbsent;
    }

    public int getTotalLate() {
        return totalLate;
    }

    public void setTotalLate(int totalLate) {
        this.totalLate = totalLate;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
