package com.srm.srmapp.repository;

import com.srm.srmapp.model.InteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionLogRepository extends JpaRepository<InteractionLog, Long> {
}
