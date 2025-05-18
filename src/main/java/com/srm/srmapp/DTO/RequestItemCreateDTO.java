package com.srm.srmapp.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestItemCreateDTO {
    private Long requestId;
    private Long productId;
    private int quantity;
}
