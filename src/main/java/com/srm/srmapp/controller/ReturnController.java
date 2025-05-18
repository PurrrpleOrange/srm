package com.srm.srmapp.controller;

import com.srm.srmapp.DTO.ReturnDTO;
import com.srm.srmapp.model.Return;
import com.srm.srmapp.model.Status;
import com.srm.srmapp.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @GetMapping
    public List<Return> getAll() {
        return returnService.getAll();
    }

    @GetMapping("/{id}")
    public Return getById(@PathVariable Long id) {
        return returnService.getById(id);
    }

    @PostMapping
    public Return create(
            @RequestParam Long deliveryId,
            @RequestParam String reason,
            @RequestParam Status status
    ) {
        return returnService.create(deliveryId, reason, status);
    }

    @GetMapping("/dto")
    public List<ReturnDTO> getAllDto() {
        return returnService.getAllDto();
    }

}
