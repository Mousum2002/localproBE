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

import com.generation.localpro.Service.OperationTypeService;
import com.generation.localpro.dto.OperationTypeDTO;
import com.generation.localpro.mapper.OperationTypeMapper;
import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.Status;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/operation-types")
public class OperationTypeController {

    private final OperationTypeService operationTypeService;
    private final OperationTypeMapper operationTypeMapper;

    public OperationTypeController(OperationTypeService operationTypeService, OperationTypeMapper operationTypeMapper) {
        this.operationTypeService = operationTypeService;
        this.operationTypeMapper = operationTypeMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationTypeDTO create(@Valid @RequestBody OperationTypeDTO operationTypeDto) {
        OperationType created = operationTypeService.create(operationTypeMapper.toEntity(operationTypeDto));
        return operationTypeMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public OperationTypeDTO update(@PathVariable Integer id, @Valid @RequestBody OperationTypeDTO operationTypeDto) {
        OperationType updated = operationTypeService.update(id, operationTypeMapper.toEntity(operationTypeDto));
        return operationTypeMapper.toDto(updated);
    }

    @GetMapping("/{id}")
    public OperationTypeDTO getById(@PathVariable Integer id) {
        return operationTypeMapper.toDto(operationTypeService.getById(id));
    }

    @GetMapping
    public List<OperationTypeDTO> getAll(@RequestParam(required = false) Status status) {
        List<OperationType> operationTypes;
        if (status != null) {
            operationTypes = operationTypeService.getByStatus(status);
        } else {
            operationTypes = operationTypeService.getAll();
        }
        return operationTypes.stream().map(operationTypeMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        operationTypeService.delete(id);
    }
}
