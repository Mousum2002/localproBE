package com.generation.localpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.Status;

public interface OperationTypeRepository extends JpaRepository<OperationType, Integer>
{
    List<OperationType> findByVendorId(Integer vendorId);
    List<OperationType> findByOperationTypeId(Integer operationTypeId);
    List<OperationType> findByStatus(Status status);
}
