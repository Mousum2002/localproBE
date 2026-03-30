package com.generation.localpro.Service;

import java.util.Arrays;
import java.util.List;

import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.exception.ResourceNotFoundException;
import com.generation.localpro.model.PortalUser;
import org.springframework.stereotype.Service;

import com.generation.localpro.repository.PortalUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.generation.localpro.mapper.PortalUserMapper;

@Service
public class PortalUserService {

    private final PasswordEncoder passwordEncoder;
    private final PortalUserRepository portalUserRepository;
    private final PortalUserMapper portalUserMapper;



    public PortalUserService(PortalUserRepository portalUserRepository, PasswordEncoder passwordEncoder, PortalUserMapper portalUserMapper) {
        this.portalUserRepository = portalUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.portalUserMapper = portalUserMapper;
        
    }

    public PortalUser create(PortalUser portalUser) {
        if (portalUserRepository.existsByUserName(portalUser.getUserName())) {
            throw new IllegalArgumentException("Username already taken");
        }
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
        // ruoli NON aggiornati — si cambiano solo da admin

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

    public List<PortalUserResponseDTO> getAll() {
        return portalUserRepository.findAll().stream()
        .map(portalUserMapper::toResponseDto)
        .toList();
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

    public PortalUserResponseDTO banUser(String userName) {
        PortalUser user = findByUserName(userName);
        user.setBanned(!user.isBanned());
        return portalUserMapper.toResponseDto( portalUserRepository.save(user));
    }

}