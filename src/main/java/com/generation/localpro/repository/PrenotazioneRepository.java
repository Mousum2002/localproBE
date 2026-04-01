package com.generation.localpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.localpro.model.Prenotazione;
import com.generation.localpro.model.PortalUser;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

    List<Prenotazione> findByUser(PortalUser user);
    List<Prenotazione> findByVendor(PortalUser vendor);

    // elimina tutte le prenotazioni che referenziano un servizio dell'utente
    @Modifying
    @Query("DELETE FROM Prenotazione p WHERE p.service.user.id = :userId")
    void deleteByServiceUserId(@Param("userId") Integer userId);
}