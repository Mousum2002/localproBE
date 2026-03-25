package com.generation.localpro.Service;

import com.generation.localpro.model.OperationTypeByVendor;
import com.generation.localpro.repository.OperationTypeByVendorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperationTypeByVendorService {

    private final OperationTypeByVendorRepository repository;

    public OperationTypeByVendorService(OperationTypeByVendorRepository repository) {
        this.repository = repository;
    }

    public OperationTypeByVendor create(OperationTypeByVendor entity) {
        return repository.save(entity);
    }

    public OperationTypeByVendor update(Integer id, OperationTypeByVendor entity) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Voce non trovata con id: " + id);
        }
        entity.setId(id);
        return repository.save(entity);
    }

    public OperationTypeByVendor getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Voce non trovata con id: " + id));
    }

    public List<OperationTypeByVendor> getAll() {
        return repository.findAll();
    }

    // Requires: List<OperationTypeByVendor> findByUserId(Integer vendorId); in the repository
    public List<OperationTypeByVendor> getByVendorId(Integer vendorId) {
        return repository.findByUserId(vendorId);
    }

    // Requires: List<OperationTypeByVendor> findByOperationTypeId(Integer id); in the repository
    public List<OperationTypeByVendor> getByOperationTypeId(Integer operationTypeId) {
        return repository.findByOperationTypeId(operationTypeId);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Voce non trovata con id: " + id);
        }
        repository.deleteById(id);
    }
}
