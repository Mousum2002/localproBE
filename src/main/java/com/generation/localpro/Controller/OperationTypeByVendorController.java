package com.generation.localpro.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public OperationTypeByVendorDTO create(@Valid @RequestBody OperationTypeByVendorDTO operationTypeByVendorDto) {
        OperationTypeByVendor created = operationTypeByVendorService.create(
            operationTypeByVendorMapper.toEntity(operationTypeByVendorDto)
        );
        return operationTypeByVendorMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public OperationTypeByVendorDTO update(@PathVariable Integer id, @Valid @RequestBody OperationTypeByVendorDTO operationTypeByVendorDto) {
        OperationTypeByVendor updated = operationTypeByVendorService.update(
            id,
            operationTypeByVendorMapper.toEntity(operationTypeByVendorDto)
        );
        return operationTypeByVendorMapper.toDto(updated);
    }

    @GetMapping("/{id}")
    public OperationTypeByVendorDTO getById(@PathVariable Integer id) {
        return operationTypeByVendorMapper.toDto(operationTypeByVendorService.getById(id));
    }

    @GetMapping
    public List<OperationTypeByVendorDTO> getAll(
        @RequestParam(required = false) Integer vendorId,
        @RequestParam(required = false) Integer operationTypeId
    ) {
        List<OperationTypeByVendor> values;
        if (vendorId != null) {
            values = operationTypeByVendorService.getByVendorId(vendorId);
        } else if (operationTypeId != null) {
            values = operationTypeByVendorService.getByOperationTypeId(operationTypeId);
        } else {
            values = operationTypeByVendorService.getAll();
        }
        return values.stream().map(operationTypeByVendorMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        operationTypeByVendorService.delete(id);
    }
}
