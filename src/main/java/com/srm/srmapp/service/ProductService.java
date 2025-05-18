package com.srm.srmapp.service;

import com.srm.srmapp.model.Product;
import com.srm.srmapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product existing = getById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setCategory(updated.getCategory());
        existing.setUnit(updated.getUnit());
        existing.setPrice(updated.getPrice());
        return productRepository.save(existing);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
