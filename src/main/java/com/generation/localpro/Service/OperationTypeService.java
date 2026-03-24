package com.generation.localpro.Service;

import java.util.List;

import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.Status;

public interface OperationTypeService {

    OperationType create(OperationType operationType);

    OperationType update(Integer id, OperationType operationType);

    OperationType getById(Integer id);

    List<OperationType> getAll();

    List<OperationType> getByStatus(Status status);

    void delete(Integer id);
}
