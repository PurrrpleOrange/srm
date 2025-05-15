package com.srm.srmapp.repository;

import com.srm.srmapp.model.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {
}
