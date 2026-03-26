package com.generation.localpro.service;

import com.generation.localpro.model.OperationTypeByVendor;
import com.generation.localpro.model.PortalUser;
import com.generation.localpro.repository.OperationTypeByVendorRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperationTypeByVendorService {

    private final OperationTypeByVendorRepository repository;
    private final PortalUserService userService;


    public OperationTypeByVendorService(OperationTypeByVendorRepository repository,PortalUserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

  public OperationTypeByVendor create(OperationTypeByVendor entity) {
   
    String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    PortalUser currentVendor = userService.findByUserName(currentUsername);
    
    if (!currentVendor.getUserName().equalsIgnoreCase(currentUsername)) {
         throw new IllegalArgumentException("Authenticated user not found " + currentUsername);
    }  
    entity.setUser(currentVendor);
    return repository.save(entity);
}


    public OperationTypeByVendor update(Integer id, OperationTypeByVendor entity) {
          String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
       PortalUser currentVendor = userService.findByUserName(currentUsername);
    
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Voce non trovata con id: " + id);
        }
         if (!currentVendor.getUserName().equalsIgnoreCase(currentUsername)) {
         throw new IllegalArgumentException("Authenticated user not found " + currentUsername);
    }  
    entity.setUser(currentVendor);
        entity.setId(id);
        return repository.save(entity);
    }

    public OperationTypeByVendor getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Voce non trovata con id: " + id));
    }

    public List<OperationTypeByVendor> getAll() {
        return repository.findAll();
    }

    // Requires: List<OperationTypeByVendor> findByUserId(Integer vendorId); in the repository
    public List<OperationTypeByVendor> getByVendorId(Integer vendorId) {
        return repository.findByUserId(vendorId);
    }

    // Requires: List<OperationTypeByVendor> findByOperationTypeId(Integer id); in the repository
    public List<OperationTypeByVendor> getByOperationTypeId(Integer operationTypeId) {
        return repository.findByOperationTypeId(operationTypeId);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Voce non trovata con id: " + id);
        }
        repository.deleteById(id);
    }
}
