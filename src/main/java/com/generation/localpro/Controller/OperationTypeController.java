package com.generation.localpro.Controller;

import com.generation.localpro.Service.OperationTypeService;
import com.generation.localpro.dto.OperationTypeDTO;
import com.generation.localpro.mapper.OperationTypeMapper;
import com.generation.localpro.model.OperationType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/operation-types")
public class OperationTypeController {

    private final OperationTypeService operationTypeService;
    private final OperationTypeMapper operationTypeMapper;

    public OperationTypeController(OperationTypeService operationTypeService,
                                   OperationTypeMapper operationTypeMapper) {
        this.operationTypeService = operationTypeService;
        this.operationTypeMapper = operationTypeMapper;
    }

    @GetMapping
    public List<OperationTypeDTO> getAll() {
        return operationTypeService.getAll()
                .stream()
                .map(operationTypeMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public OperationTypeDTO getById(@PathVariable Integer id) {
        return operationTypeMapper.toDto(operationTypeService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationTypeDTO create(@Valid @RequestBody OperationTypeDTO dto) {
        OperationType created = operationTypeService.create(operationTypeMapper.toEntity(dto));
        return operationTypeMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public OperationTypeDTO update(@PathVariable Integer id,
                                   @Valid @RequestBody OperationTypeDTO dto) {
        OperationType updated = operationTypeService.update(id, operationTypeMapper.toEntity(dto));
        return operationTypeMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        operationTypeService.delete(id);
    }

    @GetMapping("/tag/{tags}")
    public ResponseEntity<List<OperationTypeDTO>> searchByTag(@PathVariable String tags) {
        return ResponseEntity.ok(operationTypeService.searchByTag(tags));
    }
}