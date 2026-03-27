package com.generation.localpro.Service;

import org.springframework.stereotype.Service;

import com.generation.localpro.dto.OperationToListDTO;
import com.generation.localpro.mapper.OperationToListMapper;
import com.generation.localpro.repository.OperationTypeByVendorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationTolistService {

private final OperationTypeByVendorRepository vendorRepo;
    private final OperationToListMapper mapper;

    @Transactional(readOnly = true)
    public List<OperationToListDTO> getAllVendorOperations() {
        return mapper.toDtos(vendorRepo.findAll());
    }
    
}
