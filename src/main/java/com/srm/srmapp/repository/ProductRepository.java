package com.srm.srmapp.repository;

import com.srm.srmapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
