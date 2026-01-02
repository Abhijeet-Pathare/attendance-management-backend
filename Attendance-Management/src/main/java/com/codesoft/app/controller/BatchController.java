package com.codesoft.app.controller;

import com.codesoft.app.entity.Batch;
import com.codesoft.app.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping
    public Batch createBatch(@RequestBody Batch batch) {
        return batchService.create(batch);
    }

    @GetMapping
    public List<Batch> listBatches() {
        return batchService.list();
    }

    @PutMapping("/{id}")
    public Batch updateBatch(@PathVariable Long id, @RequestBody Batch batch) {
        return batchService.update(id, batch);
    }

    @DeleteMapping("/{id}")
    public void deleteBatchById(@PathVariable Long id){
         batchService.deleteById(id);
    }
}
