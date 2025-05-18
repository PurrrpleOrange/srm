package com.srm.srmapp.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplyRequestDTO {
    private Long id;
    private LocalDate createdAt;
    private String status;

    private Long supplierId;
    private String supplierName;

    private Long userId;
    private String assignedToUsername;      // ← кто будет работать с заявкой

    private Long createdById;
    private String createdByUsername;       // ← кто создал заявку
}
