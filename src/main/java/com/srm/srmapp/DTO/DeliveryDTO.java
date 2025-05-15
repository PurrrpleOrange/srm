package com.srm.srmapp.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    private Long id;
    private String date;
    private String status;
    private Long requestId;
    private String supplierName;
}
