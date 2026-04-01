package com.generation.localpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.localpro.model.Prenotazione;
import com.generation.localpro.model.PortalUser;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

    List<Prenotazione> findByUser(PortalUser user);
    List<Prenotazione> findByVendor(PortalUser vendor);
}