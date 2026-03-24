package com.generation.localpro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.localpro.model.OperationTypeByVendor;

public interface OperationTypeByVendorRepository extends JpaRepository<OperationTypeByVendor, Integer> {

    // Ricerca per l'ID dell'utente (Vendor)
    List<OperationTypeByVendor> findByUserId(Integer userId);

    // Ricerca per l'ID del tipo di operazione
    List<OperationTypeByVendor> findByOperationTypeId(Integer operationTypeId);
}