package com.generation.localpro.Service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generation.localpro.Service.OperationTypeByVendorService;
import com.generation.localpro.exception.ResourceNotFoundException;
import com.generation.localpro.model.OperationTypeByVendor;
import com.generation.localpro.repository.OperationTypeByVendorRepository;

@Service
@Transactional
public class OperationTypeByVendorServiceImpl implements OperationTypeByVendorService {

    private final OperationTypeByVendorRepository operationTypeByVendorRepository;

    public OperationTypeByVendorServiceImpl(OperationTypeByVendorRepository operationTypeByVendorRepository) {
        this.operationTypeByVendorRepository = operationTypeByVendorRepository;
    }

    @Override
    public OperationTypeByVendor create(OperationTypeByVendor operationTypeByVendor) {
        return operationTypeByVendorRepository.save(operationTypeByVendor);
    }

    @Override
    public OperationTypeByVendor update(Integer id, OperationTypeByVendor operationTypeByVendor) {
        OperationTypeByVendor existing = getById(id);
        
        // Aggiorniamo i campi dell'oggetto esistente con i nuovi dati
        existing.setUser(operationTypeByVendor.getUser());
        existing.setOperationType(operationTypeByVendor.getOperationType());
        existing.setPrice(operationTypeByVendor.getPrice());
        
        return operationTypeByVendorRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public OperationTypeByVendor getById(Integer id) {
        return operationTypeByVendorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("OperationTypeByVendor not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationTypeByVendor> getAll() {
        return operationTypeByVendorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationTypeByVendor> getByVendorId(Integer vendorId) {
        // Mappatura: il Service parla di 'Vendor', il Repository cerca nel campo 'User'
        return operationTypeByVendorRepository.findByUserId(vendorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationTypeByVendor> getByOperationTypeId(Integer operationTypeId) {
        return operationTypeByVendorRepository.findByOperationTypeId(operationTypeId);
    }

    @Override
    public void delete(Integer id) {
        OperationTypeByVendor existing = getById(id);
        operationTypeByVendorRepository.delete(existing);
    }
}