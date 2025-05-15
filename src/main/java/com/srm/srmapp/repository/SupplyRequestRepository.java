package com.srm.srmapp.repository;

import com.srm.srmapp.model.SupplyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplyRequestRepository extends JpaRepository<SupplyRequest, Long> {
    List<SupplyRequest> findByCreatedById(Long userId);
    List<SupplyRequest> findBySupplierId(Long supplierId);
}
