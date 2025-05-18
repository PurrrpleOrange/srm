package com.srm.srmapp.service;

import com.srm.srmapp.DTO.SupplyRequestDTO;
import com.srm.srmapp.model.SupplyRequest;
import com.srm.srmapp.model.Supplier;
import com.srm.srmapp.model.User;
import com.srm.srmapp.model.Status;
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

    public SupplyRequest create(Long supplierId, Long userId, Long createdById) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        User createdBy = userRepository.findById(createdById).orElseThrow();

        return SupplyRequest.builder()
                .supplier(supplier)
                .user(user)
                .createdBy(createdBy)
                .createdAt(LocalDate.now())
                .status(Status.DRAFT)
                .build();
    }

    public List<SupplyRequestDTO> getAllDto() {
        return supplyRequestRepository.findAll().stream()
                .map(req -> SupplyRequestDTO.builder()
                        .id(req.getId())
                        .createdAt(req.getCreatedAt())
                        .status(req.getStatus().name())

                        .supplierId(req.getSupplier().getId())
                        .supplierName(req.getSupplier().getName())

                        .userId(req.getUser().getId())
                        .assignedToUsername(req.getUser().getUsername())

                        .createdById(req.getCreatedBy().getId())
                        .createdByUsername(req.getCreatedBy().getUsername())
                        .build())
                .toList();
    }


}
