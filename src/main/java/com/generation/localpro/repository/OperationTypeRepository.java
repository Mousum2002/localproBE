package com.generation.localpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.OperationTypeByVendor;
import com.generation.localpro.model.Status;

public interface OperationTypeRepository extends JpaRepository<OperationType, Integer>
{
    List<OperationTypeByVendor> findByUserId(Integer userId);
    List<OperationType> findByStatus(Status status);
}
