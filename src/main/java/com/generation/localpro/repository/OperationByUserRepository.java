package com.generation.localpro.repository;

import com.generation.localpro.model.OperationByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OperationByUserRepository extends JpaRepository<OperationByUser, Integer> {

    // prenotazioni fatte da me come cliente
    List<OperationByUser> findByClientId(Integer clientId);

    // prenotazioni ricevute da me come professionista
    List<OperationByUser> findByOperationTypeByVendorUserId(Integer vendorId);
}