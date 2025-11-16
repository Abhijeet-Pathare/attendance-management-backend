package com.codesoft.app.service;

import com.codesoft.app.entity.Batch;

import java.util.List;

public interface BatchService {

    Batch create(Batch batch);

    Batch update(Long id, Batch batch);

    Batch getById(Long id);

    List<Batch> list();

    List<Batch> search(String keyword);
    // search by subject, teacherName, daysOfWeek

    void deactivate(Long id);
}
