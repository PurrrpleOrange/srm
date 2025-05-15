package com.srm.srmapp.service;

import com.srm.srmapp.model.Delivery;
import com.srm.srmapp.model.InteractionLog;
import com.srm.srmapp.model.SupplyRequest;
import com.srm.srmapp.model.User;
import com.srm.srmapp.repository.InteractionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InteractionLogService {

    private final InteractionLogRepository logRepository;

    @Autowired
    public InteractionLogService(InteractionLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void logAction(String action, String message,
                          User user, SupplyRequest request, Delivery delivery) {
        InteractionLog log = InteractionLog.builder()
                .timestamp(LocalDateTime.now())
                .action(action)
                .message(message)
                .user(user)
                .supplyRequest(request)
                .delivery(delivery)
                .build();

        logRepository.save(log);
    }
}
