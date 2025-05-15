package com.srm.srmapp.controller;

import com.srm.srmapp.model.RequestItem;
import com.srm.srmapp.repository.RequestItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request-items")
public class RequestItemController {

    private final RequestItemRepository requestItemRepository;

    @Autowired
    public RequestItemController(RequestItemRepository requestItemRepository) {
        this.requestItemRepository = requestItemRepository;
    }

    @GetMapping
    public List<RequestItem> getAllItems() {
        return requestItemRepository.findAll();
    }

    @PostMapping
    public RequestItem createItem(@RequestBody RequestItem item) {
        return requestItemRepository.save(item);
    }
}
