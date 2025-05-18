package com.srm.srmapp.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDTO {
    private Long id;
    private LocalDate deliveryDate;
    private String status;

    private Long requestId;
    private LocalDate requestCreatedAt;

    private Long supplierId;
    private String supplierName;
}
