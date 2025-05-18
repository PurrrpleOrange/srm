package com.srm.srmapp.repository;

import com.srm.srmapp.model.ReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnItemRepository extends JpaRepository<ReturnItem, Long> {
    List<ReturnItem> findByDeliveryReturnId(Long returnId);
}
