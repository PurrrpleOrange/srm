package com.srm.srmapp.controller;

import com.srm.srmapp.DTO.SupplyRequestDTO;
import com.srm.srmapp.model.SupplyRequest;
import com.srm.srmapp.service.SupplyRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class SupplyRequestController {

    private final SupplyRequestService supplyRequestService;

    @GetMapping
    public List<SupplyRequest> getAll() {
        return supplyRequestService.getAll();
    }

    @GetMapping("/{id}")
    public SupplyRequest getById(@PathVariable Long id) {
        return supplyRequestService.getById(id);
    }

    @PostMapping
    public SupplyRequest create(
            @RequestParam Long supplierId,
            @RequestParam Long userId,
            @RequestParam Long createdById
    ) {
        return supplyRequestService.create(supplierId, userId, createdById);
    }

    @GetMapping("/dto")
    public List<SupplyRequestDTO> getAllDto() {
        return supplyRequestService.getAllDto();
    }
}
