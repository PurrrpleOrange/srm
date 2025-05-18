package com.srm.srmapp.controller;

import com.srm.srmapp.DTO.RequestItemCreateDTO;
import com.srm.srmapp.DTO.RequestItemDTO;
import com.srm.srmapp.model.RequestItem;
import com.srm.srmapp.service.RequestItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request-items")
@RequiredArgsConstructor
public class RequestItemController {

    private final RequestItemService requestItemService;

    @PostMapping
    public RequestItem create(@RequestBody RequestItemCreateDTO dto) {
        return requestItemService.create(dto);
    }

    @GetMapping("/by-request/{requestId}")
    public List<RequestItemDTO> getByRequest(@PathVariable Long requestId) {
        return requestItemService.getByRequestId(requestId);
    }
}
