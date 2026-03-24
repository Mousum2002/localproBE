package com.generation.localpro.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.exception.ResourceNotFoundException;
import com.generation.localpro.model.PortalUser;
import com.generation.localpro.model.Role;
import com.generation.localpro.repository.PortalUserRepository;

@Service
@Transactional
public class PortalUserServiceImpl implements PortalUserService {

    private final PortalUserRepository portalUserRepository;

    public PortalUserServiceImpl(PortalUserRepository portalUserRepository) {
        this.portalUserRepository = portalUserRepository;
    }

    @Override
    public PortalUser create(PortalUser portalUser) {
        return portalUserRepository.save(portalUser);
    }

    @Override
    public PortalUser update(Integer id, PortalUser portalUser) {
        PortalUser existing = getById(id);
        existing.setFirstName(portalUser.getFirstName());
        existing.setLastName(portalUser.getLastName());
        existing.setEmail(portalUser.getEmail());
        existing.setPassword(portalUser.getPassword());
        existing.setCity(portalUser.getCity());
        existing.setAddress(portalUser.getAddress());
        existing.setRole(portalUser.getRole());
        existing.setBio(portalUser.getBio());
        existing.setX(portalUser.getX());
        existing.setY(portalUser.getY());
        return portalUserRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public PortalUser getById(Integer id) {
        return portalUserRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("PortalUser not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PortalUser> getAll() {
        return portalUserRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PortalUser> getByRole(Role role) {
        return portalUserRepository.findByRole(role);
    }

    @Override
    public void delete(Integer id) {
        PortalUser existing = getById(id);
        portalUserRepository.delete(existing);
    }
}
