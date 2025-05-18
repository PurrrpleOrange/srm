package com.srm.srmapp.service;

import com.srm.srmapp.DTO.ReturnItemDTO;
import com.srm.srmapp.model.ReturnItem;
import com.srm.srmapp.model.Product;
import com.srm.srmapp.model.Return;
import com.srm.srmapp.repository.ProductRepository;
import com.srm.srmapp.repository.ReturnItemRepository;
import com.srm.srmapp.repository.ReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnItemService {

    private final ReturnItemRepository returnItemRepository;
    private final ReturnRepository returnRepository;
    private final ProductRepository productRepository;

    public List<ReturnItem> getByReturnId(Long returnId) {
        return returnItemRepository.findByDeliveryReturnId(returnId);
    }

    public ReturnItem create(Long returnId, Long productId, int quantity, String comment) {
        Return ret = returnRepository.findById(returnId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        ReturnItem item = ReturnItem.builder()
                .deliveryReturn(ret)
                .product(product)
                .quantity(quantity)
                .comment(comment)
                .build();

        return returnItemRepository.save(item);
    }

    public List<ReturnItemDTO> getDtoByReturnId(Long returnId) {
        return returnItemRepository.findByDeliveryReturnId(returnId).stream()
                .map(item -> ReturnItemDTO.builder()
                        .id(item.getId())
                        .returnId(item.getDeliveryReturn().getId())
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .comment(item.getComment())
                        .build())
                .toList();
    }

}
