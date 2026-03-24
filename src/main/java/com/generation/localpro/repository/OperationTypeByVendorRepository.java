package com.generation.localpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.localpro.model.OperationTypeByVendor;

public interface OperationTypeByVendorRepository extends JpaRepository<OperationTypeByVendor, Integer>
{
    List<OperationTypeByVendor> findByVendorId(Integer vendorId);
    List<OperationTypeByVendor> findByOperationTypeId(Integer operationTypeId);
}
