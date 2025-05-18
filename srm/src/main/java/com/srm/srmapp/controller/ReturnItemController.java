package com.srm.srmapp.controller;

import com.srm.srmapp.DTO.ReturnItemDTO;
import com.srm.srmapp.model.ReturnItem;
import com.srm.srmapp.service.ReturnItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/return-items")
@RequiredArgsConstructor
public class ReturnItemController {

    private final ReturnItemService returnItemService;

    @GetMapping("/by-return/{returnId}")
    public List<ReturnItem> getByReturn(@PathVariable Long returnId) {
        return returnItemService.getByReturnId(returnId);
    }

    @PostMapping
    public ReturnItem create(
            @RequestParam Long returnId,
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam(required = false) String comment
    ) {
        return returnItemService.create(returnId, productId, quantity, comment);
    }

    @GetMapping("/dto/by-return/{returnId}")
    public List<ReturnItemDTO> getByReturnIdDto(@PathVariable Long returnId) {
        return returnItemService.getDtoByReturnId(returnId);
    }

}
