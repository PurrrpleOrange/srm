package com.srm.srmapp.controller;

import com.srm.srmapp.DTO.DeliveryDTO;
import com.srm.srmapp.model.Delivery;
import com.srm.srmapp.repository.DeliveryRepository;
import com.srm.srmapp.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryRepository deliveryRepository, DeliveryService deliveryService) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @GetMapping
    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryRepository.findAll().stream().map(delivery -> {
            DeliveryDTO dto = new DeliveryDTO();
            dto.setId(delivery.getId());
            dto.setDate(delivery.getDate().toString());
            dto.setStatus(delivery.getStatus().name());

            if (delivery.getSupplyRequest() != null) {
                dto.setRequestId(delivery.getSupplyRequest().getId());
                if (delivery.getSupplyRequest().getSupplier() != null) {
                    dto.setSupplierName(delivery.getSupplyRequest().getSupplier().getName());
                }
            }

            return dto;
        }).toList();
    }

}
