package com.srm.srmapp.service;

import com.srm.srmapp.DTO.DeliveryDTO;
import com.srm.srmapp.model.Delivery;
import com.srm.srmapp.model.Status;
import com.srm.srmapp.model.SupplyRequest;
import com.srm.srmapp.model.Supplier;
import com.srm.srmapp.repository.DeliveryRepository;
import com.srm.srmapp.repository.SupplyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final SupplyRequestRepository supplyRequestRepository;

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    public Delivery getById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Поставка не найдена"));
    }

    public Delivery create(Long requestId, Status status, LocalDate deliveryDate) {
        SupplyRequest request = supplyRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        Supplier supplier = request.getSupplier();

        Delivery delivery = Delivery.builder()
                .supplyRequest(request)
                .supplier(supplier)
                .status(status)
                .deliveryDate(deliveryDate != null ? deliveryDate : LocalDate.now()) // <-- вот тут
                .build();

        return deliveryRepository.save(delivery);
    }


    public List<DeliveryDTO> getAllDto() {
        try {
            return deliveryRepository.findAll().stream()
                    .map(delivery -> {
                        // Пошаговая проверка
                        if (delivery.getSupplyRequest() == null) {
                            throw new RuntimeException("У поставки нет заявки (supplyRequest = null)");
                        }
                        if (delivery.getSupplier() == null) {
                            throw new RuntimeException("У поставки нет поставщика (supplier = null)");
                        }

                        return DeliveryDTO.builder()
                                .id(delivery.getId())
                                .deliveryDate(delivery.getDeliveryDate())
                                .status(delivery.getStatus().name())
                                .requestId(delivery.getSupplyRequest().getId())
                                .requestCreatedAt(delivery.getSupplyRequest().getCreatedAt())
                                .supplierId(delivery.getSupplier().getId())
                                .supplierName(delivery.getSupplier().getName())
                                .build();
                    })
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка в DeliveryService.getAllDto: " + e.getMessage(), e);
        }
    }


}
