package com.srm.srmapp.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnDTO {
    private Long id;
    private LocalDate returnDate;
    private String status;
    private String reason;

    private Long deliveryId;
    private Long supplierId;
    private String supplierName;
}
