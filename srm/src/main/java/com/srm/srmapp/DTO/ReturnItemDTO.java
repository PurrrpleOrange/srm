package com.srm.srmapp.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnItemDTO {
    private Long id;
    private Long returnId;

    private Long productId;
    private String productName;

    private Integer quantity;
    private String comment;
}
