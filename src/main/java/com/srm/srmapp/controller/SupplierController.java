package com.srm.srmapp.controller;

import com.srm.srmapp.model.Supplier;
import com.srm.srmapp.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier) {
        return supplierRepository.findById(id)
                .map(existingSupplier -> {
                    existingSupplier.setName(updatedSupplier.getName());
                    existingSupplier.setContactEmail(updatedSupplier.getContactEmail());
                    existingSupplier.setPhoneNumber(updatedSupplier.getPhoneNumber());
                    existingSupplier.setCategory(updatedSupplier.getCategory());
                    existingSupplier.setTaxId(updatedSupplier.getTaxId());
                    existingSupplier.setAddress(updatedSupplier.getAddress());
                    existingSupplier.setActive(updatedSupplier.getActive());
                    return supplierRepository.save(existingSupplier);
                })
                .orElseThrow(() -> new RuntimeException("Поставщик не найден с ID: " + id));
    }

}
