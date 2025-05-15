package com.srm.srmapp.service;

import com.srm.srmapp.model.Delivery;
import com.srm.srmapp.model.InteractionLog;
import com.srm.srmapp.model.SupplyRequest;
import com.srm.srmapp.repository.DeliveryRepository;
import com.srm.srmapp.repository.InteractionLogRepository;
import com.srm.srmapp.repository.SupplyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final SupplyRequestRepository supplyRequestRepository;
    private final InteractionLogRepository logRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository,
                           SupplyRequestRepository supplyRequestRepository,
                           InteractionLogRepository logRepository) {
        this.deliveryRepository = deliveryRepository;
        this.supplyRequestRepository = supplyRequestRepository;
        this.logRepository = logRepository;
    }

    public Delivery createDelivery(Delivery delivery) {
        // Сохраняем поставку
        Delivery saved = deliveryRepository.save(delivery);

        // Обновляем статус заявки
        SupplyRequest request = saved.getSupplyRequest();
        request.setStatus(SupplyRequest.Status.DELIVERED);
        supplyRequestRepository.save(request);

        // Логируем событие
        InteractionLog log = InteractionLog.builder()
                .timestamp(LocalDateTime.now())
                .action("DELIVERY_RECEIVED")
                .message("Поставка принята по заявке ID = " + request.getId())
                .supplyRequest(request)
                .delivery(saved)
                .user(null) // если в будущем будет security — можно будет подтягивать пользователя
                .build();

        logRepository.save(log);

        return saved;
    }
}
