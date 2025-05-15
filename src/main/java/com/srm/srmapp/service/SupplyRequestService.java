package com.srm.srmapp.service;

import com.srm.srmapp.model.SupplyRequest;
import com.srm.srmapp.model.Supplier;
import com.srm.srmapp.model.User;
import com.srm.srmapp.repository.SupplyRequestRepository;
import com.srm.srmapp.repository.SupplierRepository;
import com.srm.srmapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyRequestService {

    private final SupplyRequestRepository supplyRequestRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;

    public List<SupplyRequest> getAll() {
        return supplyRequestRepository.findAll();
    }

    public SupplyRequest getById(Long id) {
        return supplyRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
    }

    public SupplyRequest create(Long supplierId, Long userId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Поставщик не найден"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        SupplyRequest request = SupplyRequest.builder()
                .createdAt(LocalDate.now())
                .status(SupplyRequest.Status.CREATED)
                .supplier(supplier)
                .createdBy(user)
                .build();

        return supplyRequestRepository.save(request);
    }

    public void cancel(Long id) {
        SupplyRequest req = getById(id);
        req.setStatus(SupplyRequest.Status.CANCELED);
        supplyRequestRepository.save(req);
    }

    public void markDelivered(Long id) {
        SupplyRequest req = getById(id);
        req.setStatus(SupplyRequest.Status.DELIVERED);
        supplyRequestRepository.save(req);
    }
}
