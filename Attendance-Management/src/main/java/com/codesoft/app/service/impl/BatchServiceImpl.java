package com.codesoft.app.service.impl;

import com.codesoft.app.entity.Batch;
import com.codesoft.app.repository.BatchRepository;
import com.codesoft.app.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Batch create(Batch batch) {
        batch.setStatus("ACTIVE");
        return batchRepository.save(batch);
    }

    @Override
    public Batch update(Long id, Batch batch) {
        Optional<Batch> optional = batchRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Batch not found with id: " + id);
        }
        Batch existing = optional.get();
        existing.setSubject(batch.getSubject());
        existing.setTeacherName(batch.getTeacherName());
        existing.setStartTime(batch.getStartTime());
        existing.setEndTime(batch.getEndTime());
        existing.setDaysOfWeek(batch.getDaysOfWeek());
        existing.setMaxCapacity(batch.getMaxCapacity());
        existing.setStatus(batch.getStatus());
        return batchRepository.save(existing);
    }

    @Override
    public Batch getById(Long id) {
        return batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + id));
    }

    @Override
    public List<Batch> list() {
        return batchRepository.findAll();
    }

    @Override
    public List<Batch> search(String keyword) {
        List<Batch> bySubject = batchRepository.findBySubjectContainingIgnoreCase(keyword);
        if (!bySubject.isEmpty()) return bySubject;
        List<Batch> byTeacher = batchRepository.findByTeacherNameContainingIgnoreCase(keyword);
        if (!byTeacher.isEmpty()) return byTeacher;
        List<Batch> byDay = batchRepository.findByDaysOfWeekContainingIgnoreCase(keyword);
        return byDay;
    }

    @Override
    public void deactivate(Long id) {
        Batch batch = getById(id);
        batch.setStatus("INACTIVE");
        batchRepository.save(batch);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Batch> existing = batchRepository.findById(id);
        Batch b = existing.get();
        batchRepository.deleteById(b.getId());
    }
}
