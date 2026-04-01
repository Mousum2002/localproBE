package com.generation.localpro.Service;

import java.util.Arrays;
import java.util.List;

import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.exception.ResourceNotFoundException;
import com.generation.localpro.model.PortalUser;
import org.springframework.stereotype.Service;
import com.generation.localpro.repository.PortalUserRepository;
import com.generation.localpro.repository.PrenotazioneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.generation.localpro.mapper.PortalUserMapper;
import jakarta.transaction.Transactional;

@Service
public class PortalUserService {

    private final PasswordEncoder passwordEncoder;
    private final PortalUserRepository portalUserRepository;
    private final PortalUserMapper portalUserMapper;
    private final PrenotazioneRepository prenotazioneRepository;



    public PortalUserService(PortalUserRepository portalUserRepository, PasswordEncoder passwordEncoder, PortalUserMapper portalUserMapper, PrenotazioneRepository prenotazioneRepository) {
        this.portalUserRepository = portalUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.portalUserMapper = portalUserMapper;
        this.prenotazioneRepository = prenotazioneRepository;
        
    }

    public PortalUser create(PortalUser portalUser) {
        if (portalUserRepository.existsByUserName(portalUser.getUserName())) {
            throw new IllegalArgumentException("Username already taken");
        }
        portalUser.setPassword(passwordEncoder.encode(portalUser.getPassword()));
        portalUser.setRoles(Arrays.asList("USER"));
        portalUser.setBanned(false);
        return portalUserRepository.save(portalUser);
    }

    public PortalUser update(PortalUser updated) {
       

        if (updated.getPassword() != null
                && !updated.getPassword().isBlank()) {
            updated.setPassword(passwordEncoder.encode(updated.getPassword()));
        }
        return portalUserRepository.save(updated);
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

    @Transactional
    public void delete(Integer id) {
        if (!portalUserRepository.existsById(id)) {
            throw new EntityNotFoundException("Utente non trovato con id: " + id);
        }

        prenotazioneRepository.deleteByServiceUserId(id);
        prenotazioneRepository.deleteByUserId(id);
        prenotazioneRepository.deleteByVendorId(id);
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