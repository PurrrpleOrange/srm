package com.srm.srmapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "returns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate returnDate;
    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status; // REJECTED / PARTIAL / RECEIVED

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}
