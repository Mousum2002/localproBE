package com.generation.localpro.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generation.localpro.Service.OperationTypeService;
import com.generation.localpro.exception.ResourceNotFoundException;
import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.Status;
import com.generation.localpro.repository.OperationTypeRepository;

@Service
@Transactional
public class OperationTypeServiceImpl implements OperationTypeService {

    private final OperationTypeRepository operationTypeRepository;

    public OperationTypeServiceImpl(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public OperationType create(OperationType operationType) {
        return operationTypeRepository.save(operationType);
    }

    @Override
    public OperationType update(Integer id, OperationType operationType) {
        OperationType existing = getById(id);
        existing.setName(operationType.getName());
        existing.setDescription(operationType.getDescription());
        existing.setTags(operationType.getTags());
        existing.setStatus(operationType.getStatus());
        return operationTypeRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public OperationType getById(Integer id) {
        return operationTypeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("OperationType not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationType> getAll() {
        return operationTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationType> getByStatus(Status status) {
        return operationTypeRepository.findByStatus(status);
    }

    @Override
    public void delete(Integer id) {
        OperationType existing = getById(id);
        operationTypeRepository.delete(existing);
    }
}
