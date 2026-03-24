package com.generation.localpro.Service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generation.localpro.Service.OperationTypeService;
import com.generation.localpro.exception.ResourceNotFoundException;
import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.Status;
import com.generation.localpro.repository.OperationTypeRepository;

import lombok.RequiredArgsConstructor; // Opzionale: se usi Lombok

@Service
@Transactional
// @RequiredArgsConstructor // Se scommenti questo, puoi togliere il costruttore manuale
public class OperationTypeServiceImpl implements OperationTypeService {

    private final OperationTypeRepository operationTypeRepository;

    public OperationTypeServiceImpl(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public OperationType create(OperationType operationType) {
        // È buona norma assicurarsi che l'ID sia nullo per una creazione 
        return operationTypeRepository.save(operationType);
    }

    @Override
    public OperationType update(Integer id, OperationType operationType) {
        // Usiamo l'ID del path per garantire la coerenza
        return operationTypeRepository.findById(id)
            .map(existing -> {
                existing.setName(operationType.getName());
                existing.setDescription(operationType.getDescription());
                existing.setTags(operationType.getTags());
                existing.setStatus(operationType.getStatus());
                // In Spring Data JPA, save() su un oggetto con ID esistente fa l'update
                return operationTypeRepository.save(existing);
            })
            .orElseThrow(() -> new ResourceNotFoundException("OperationType not found with id: " + id));
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
        // Verifichiamo l'esistenza prima di cancellare per lanciare l'eccezione corretta
        if (!operationTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete: OperationType not found with id: " + id);
        }
        operationTypeRepository.deleteById(id);
    }
}