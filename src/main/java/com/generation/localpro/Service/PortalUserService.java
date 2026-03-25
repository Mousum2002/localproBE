package com.generation.localpro.Service;

import java.util.List;

import com.generation.localpro.model.PortalUser;
import org.springframework.stereotype.Service;

import com.generation.localpro.repository.PortalUserRepository;
import jakarta.persistence.EntityNotFoundException;


@Service
public class PortalUserService {

    private final PortalUserRepository portalUserRepository;

    public PortalUserService(PortalUserRepository portalUserRepository) {
        this.portalUserRepository = portalUserRepository;
    }

    public PortalUser create(PortalUser portalUser) {
        return portalUserRepository.save(portalUser);
    }

    public PortalUser update(Integer id, PortalUser portalUser) {
        if (!portalUserRepository.existsById(id)) {
            throw new EntityNotFoundException("Utente non trovato con id: " + id);
        }
        portalUser.setId(id);
        return portalUserRepository.save(portalUser);
    }

    public PortalUser getById(Integer id) {
        return portalUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con id: " + id));
    }

    public List<PortalUser> getAll() {
        return portalUserRepository.findAll();
    }


    public void delete(Integer id) {
        if (!portalUserRepository.existsById(id)) {
            throw new EntityNotFoundException("Utente non trovato con id: " + id);
        }
        portalUserRepository.deleteById(id);
    }
}
