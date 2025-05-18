package com.srm.srmapp.service;

import com.srm.srmapp.DTO.RequestItemCreateDTO;
import com.srm.srmapp.DTO.RequestItemDTO;
import com.srm.srmapp.model.Product;
import com.srm.srmapp.model.RequestItem;
import com.srm.srmapp.model.SupplyRequest;
import com.srm.srmapp.repository.ProductRepository;
import com.srm.srmapp.repository.RequestItemRepository;
import com.srm.srmapp.repository.SupplyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestItemService {

    private final RequestItemRepository requestItemRepository;
    private final SupplyRequestRepository supplyRequestRepository;
    private final ProductRepository productRepository;

    public RequestItem create(RequestItemCreateDTO dto) {
        SupplyRequest request = supplyRequestRepository.findById(dto.getRequestId())
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Товар не найден"));

        RequestItem item = RequestItem.builder()
                .supplyRequest(request)
                .product(product)
                .quantity(dto.getQuantity())
                .unit(product.getUnit())
                .unitPrice(product.getPrice())
                .build();

        return requestItemRepository.save(item);
    }

    public List<RequestItemDTO> getByRequestId(Long requestId) {
        return requestItemRepository.findBySupplyRequestId(requestId).stream()
                .map(item -> RequestItemDTO.builder()
                        .id(item.getId())
                        .quantity(item.getQuantity())
                        .product(item.getProduct())
                        .build())
                .collect(Collectors.toList());
    }
}
