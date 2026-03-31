package com.generation.localpro.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.localpro.Service.OperationTypeByVendorService;
import com.generation.localpro.dto.OperationTypeByVendorDTO;
import com.generation.localpro.mapper.OperationTypeByVendorMapper;
import com.generation.localpro.model.OperationTypeByVendor;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/vendor-operations")
public class OperationTypeByVendorController {

    private final OperationTypeByVendorService operationTypeByVendorService;
    private final OperationTypeByVendorMapper operationTypeByVendorMapper;

    public OperationTypeByVendorController(
        OperationTypeByVendorService operationTypeByVendorService,
        OperationTypeByVendorMapper operationTypeByVendorMapper
    ) {
        this.operationTypeByVendorService = operationTypeByVendorService;
        this.operationTypeByVendorMapper = operationTypeByVendorMapper;
    }

    @PostMapping
    public ResponseEntity<OperationTypeByVendorDTO> create(@Valid @RequestBody OperationTypeByVendorDTO operationTypeByVendorDto) {
        OperationTypeByVendor created = operationTypeByVendorService.create(
            operationTypeByVendorMapper.toEntity(operationTypeByVendorDto)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(operationTypeByVendorMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationTypeByVendorDTO> update(@PathVariable Integer id, @Valid @RequestBody OperationTypeByVendorDTO operationTypeByVendorDto) {
        OperationTypeByVendor updated = operationTypeByVendorService.update(
            id,
            operationTypeByVendorMapper.toEntity(operationTypeByVendorDto)
        );
        return ResponseEntity.ok(operationTypeByVendorMapper.toDto(updated));
    }

    @GetMapping
    public List<OperationTypeByVendorDTO> getAllPerUser() {
        return operationTypeByVendorService.getAll().stream().map(operationTypeByVendorMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    operationTypeByVendorService.delete(id);
       return ResponseEntity.noContent().build();
    }
}
