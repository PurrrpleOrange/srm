package com.srm.srmapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String articleNumber; // артикул

    @Column(nullable = false)
    private String name;

    private String description;

    private String category;

    private String unit; // единица измерения (шт, кг и т.д.)
}
