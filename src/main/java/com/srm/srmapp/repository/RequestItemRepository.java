package com.srm.srmapp.repository;

import com.srm.srmapp.model.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {
    List<RequestItem> findBySupplyRequestId(Long requestId);
}
