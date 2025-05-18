package com.srm.srmapp.service;

import com.srm.srmapp.model.Supplier;
import com.srm.srmapp.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Supplier getById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Поставщик не найден"));
    }

    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier update(Long id, Supplier updated) {
        Supplier existing = getById(id);
        existing.setName(updated.getName());
        existing.setContactEmail(updated.getContactEmail());
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setCategory(updated.getCategory());
        existing.setTaxId(updated.getTaxId());
        existing.setAddress(updated.getAddress());
        existing.setActive(updated.getActive());
        return supplierRepository.save(existing);
    }

    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
}
