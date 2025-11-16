package com.codesoft.app.repository;

import com.codesoft.app.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    List<Batch> findBySubjectContainingIgnoreCase(String subject);

    List<Batch> findByTeacherNameContainingIgnoreCase(String teacherName);

    List<Batch> findByDaysOfWeekContainingIgnoreCase(String day);

    List<Batch> findByStatus(String status);
}
