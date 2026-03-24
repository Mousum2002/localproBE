package com.generation.localpro.Service;

import java.util.List;

import com.generation.localpro.model.OperationTypeByVendor;

public interface OperationTypeByVendorService {

    OperationTypeByVendor create(OperationTypeByVendor operationTypeByVendor);

    OperationTypeByVendor update(Integer id, OperationTypeByVendor operationTypeByVendor);

    OperationTypeByVendor getById(Integer id);

    List<OperationTypeByVendor> getAll();

    List<OperationTypeByVendor> getByVendorId(Integer vendorId);

    List<OperationTypeByVendor> getByOperationTypeId(Integer operationTypeId);

    void delete(Integer id);
}
