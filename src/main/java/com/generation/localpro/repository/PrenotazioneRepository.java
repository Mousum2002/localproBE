package com.generation.localpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.localpro.model.Prenotazione;
import com.generation.localpro.model.PortalUser;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {



 @Modifying
    @Query("DELETE FROM Prenotazione p WHERE p.service.user.id = :userId")
    void deleteByServiceUserId(@Param("userId") Integer userId);

    @Modifying
    @Query("DELETE FROM Prenotazione p WHERE p.user.id = :userId")
    void deleteByUserId(@Param("userId") Integer userId);

    @Modifying
    @Query("DELETE FROM Prenotazione p WHERE p.vendor.id = :userId")
    void deleteByVendorId(@Param("userId") Integer userId);}