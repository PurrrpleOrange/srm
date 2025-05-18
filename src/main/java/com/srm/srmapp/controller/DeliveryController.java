package com.srm.srmapp.controller;

import com.srm.srmapp.DTO.DeliveryDTO;
import com.srm.srmapp.model.Delivery;
import com.srm.srmapp.model.Status;
import com.srm.srmapp.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public List<Delivery> getAll() {
        return deliveryService.getAll();
    }

    @GetMapping("/{id}")
    public Delivery getById(@PathVariable Long id) {
        return deliveryService.getById(id);
    }

    @PostMapping
    public Delivery create(
            @RequestParam Long requestId,
            @RequestParam Status status,
            @RequestParam(required = false) String deliveryDate
    ) {
        LocalDate parsedDate = deliveryDate != null ? LocalDate.parse(deliveryDate) : null;
        return deliveryService.create(requestId, status, parsedDate);
    }


    @GetMapping("/dto")
    public List<DeliveryDTO> getAllDto() {
        return deliveryService.getAllDto();
    }

}
