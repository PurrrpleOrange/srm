package com.srm.srmapp.DTO;

import lombok.*;
import com.srm.srmapp.model.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestItemDTO {
    private Long id;
    private int quantity;
    private Product product;
}
