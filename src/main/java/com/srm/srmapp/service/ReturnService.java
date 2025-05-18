package com.srm.srmapp.service;

import com.srm.srmapp.DTO.ReturnDTO;
import com.srm.srmapp.model.Delivery;
import com.srm.srmapp.model.Return;
import com.srm.srmapp.model.Status;
import com.srm.srmapp.repository.DeliveryRepository;
import com.srm.srmapp.repository.ReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnService {

    private final ReturnRepository returnRepository;
    private final DeliveryRepository deliveryRepository;

    public List<Return> getAll() {
        return returnRepository.findAll();
    }

    public Return getById(Long id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Возврат не найден"));
    }

    public Return create(Long deliveryId, String reason, Status status) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Поставка не найдена"));

        Return ret = Return.builder()
                .delivery(delivery)
                .reason(reason)
                .status(status)
                .returnDate(LocalDate.now())
                .build();

        return returnRepository.save(ret);
    }

    public List<ReturnDTO> getAllDto() {
        return returnRepository.findAll().stream()
                .map(ret -> ReturnDTO.builder()
                        .id(ret.getId())
                        .returnDate(ret.getReturnDate())
                        .status(ret.getStatus().name())
                        .reason(ret.getReason())

                        .deliveryId(ret.getDelivery().getId())
                        .supplierId(ret.getDelivery().getSupplier().getId())
                        .supplierName(ret.getDelivery().getSupplier().getName())
                        .build())
                .toList();
    }

}
