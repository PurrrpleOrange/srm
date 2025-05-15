package com.srm.srmapp.controller;

import com.srm.srmapp.model.InteractionLog;
import com.srm.srmapp.repository.InteractionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class InteractionLogController {

    private final InteractionLogRepository logRepository;

    @Autowired
    public InteractionLogController(InteractionLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping
    public List<InteractionLog> getAllLogs() {
        return logRepository.findAll();
    }

    @PostMapping
    public InteractionLog createLog(@RequestBody InteractionLog log) {
        return logRepository.save(log);
    }
}
