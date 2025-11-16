package com.codesoft.app.repository;

import com.codesoft.app.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByBatchId(Long batchId);

    List<Attendance> findByDate(String date);

    List<Attendance> findByStudentIdAndBatchIdAndDate(Long studentId, Long batchId, String date);
    List<Attendance> findByBatchIdAndDate(Long batchId,String date);
    boolean existsByStudentIdAndBatchIdAndDate(Long studentId, Long batchId, String date);

    List<Attendance> findByStudentIdAndDateStartingWith(Long studentId, String yearMonth);

}
