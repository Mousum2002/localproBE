package com.generation.localpro.service;


import com.generation.localpro.dto.OperationTypeDTO;
import com.generation.localpro.mapper.OperationTypeMapper;
import com.generation.localpro.model.OperationType;

import com.generation.localpro.repository.OperationTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperationTypeService {

    private final OperationTypeRepository operationTypeRepository;
    private final OperationTypeMapper mapper;


    public OperationTypeService(OperationTypeRepository operationTypeRepository, OperationTypeMapper mapper) {
        this.operationTypeRepository = operationTypeRepository;
        this.mapper = mapper;
    }

    public OperationType create(OperationType operationType) {
        return operationTypeRepository.save(operationType);
    }

    public OperationType update(Integer id, OperationType operationType) {
        if (!operationTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Operazione non trovata con id: " + id);
        }
        operationType.setId(id);
        return operationTypeRepository.save(operationType);
    }

    public OperationType getById(Integer id) {
        return operationTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Operazione non trovata con id: " + id));
    }

    public List<OperationType> getAll() {
        return operationTypeRepository.findAll();
    }

  
    public void delete(Integer id) {
        if (!operationTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Operazione non trovata con id: " + id);
        }
        operationTypeRepository.deleteById(id);
    }

    public List<OperationTypeDTO> searchByTag (String searchTerme ) {
          
      List<OperationType> results = operationTypeRepository.findByOperationType(searchTerme);
      return mapper.toDtos(results);
    }
}