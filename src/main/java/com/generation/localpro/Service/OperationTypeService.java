package com.generation.localpro.Service;


import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.Status;
import com.generation.localpro.repository.OperationTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperationTypeService {

    private final OperationTypeRepository operationTypeRepository;

    public OperationTypeService(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
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

    // Requires: List<OperationType> findByStatus(Status status); in the repository
    public List<OperationType> getByStatus(Status status) {
        return operationTypeRepository.findByStatus(status);
    }

    public void delete(Integer id) {
        if (!operationTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Operazione non trovata con id: " + id);
        }
        operationTypeRepository.deleteById(id);
    }
}
