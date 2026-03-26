package com.generation.localpro.Service;

import java.util.Arrays;
import java.util.List;

import com.generation.localpro.exception.ResourceNotFoundException;
import com.generation.localpro.model.PortalUser;
import org.springframework.stereotype.Service;

import com.generation.localpro.repository.PortalUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class PortalUserService {

    private final PasswordEncoder passwordEncoder;
    private final PortalUserRepository portalUserRepository;

    public PortalUserService(PortalUserRepository portalUserRepository, PasswordEncoder passwordEncoder) {
        this.portalUserRepository = portalUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PortalUser create(PortalUser portalUser) {
        portalUser.setPassword(passwordEncoder.encode(portalUser.getPassword()));
        portalUser.setRoles(Arrays.asList("USER"));
        return portalUserRepository.save(portalUser);
    }

   public PortalUser update(Integer id, PortalUser updated) {
    PortalUser existing = portalUserRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));

    existing.setUserName(updated.getUserName());
    existing.setEmail(updated.getEmail());
    existing.setCity(updated.getCity());
    existing.setAddress(updated.getAddress());
    existing.setBio(updated.getBio());
    existing.setFirstName(updated.getFirstName());
    existing.setLastName(updated.getLastName());
    existing.setProfileImage(updated.getProfileImage());
    existing.setX(updated.getX());
    existing.setY(updated.getY());
    existing.setRoles(updated.getRoles());

    // ← Aggiorna la password SOLO se non è il placeholder
    if (updated.getPassword() != null
            && !updated.getPassword().equals("UNCHANGED")
            && !updated.getPassword().isBlank()) {
        existing.setPassword(passwordEncoder.encode(updated.getPassword()));
    }

    return portalUserRepository.save(existing);
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
    public PortalUser findByUserName(String userName) {
		return portalUserRepository.findByUserName(userName)
				.orElseThrow(() -> new IllegalArgumentException("User not found: " + userName));
	}

    public void banUser(Integer id) {
        PortalUser user = portalUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.isBanned()) {
            throw new EntityNotFoundException("User is already banned");
        }
        user.setBanned(true);
        portalUserRepository.save(user);
    }


}
