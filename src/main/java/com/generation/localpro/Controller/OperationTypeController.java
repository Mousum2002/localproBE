package com.generation.localpro.Controller;


import com.generation.localpro.dto.OperationTypeDTO;
import com.generation.localpro.mapper.OperationTypeMapper;
import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.Status;
import com.generation.localpro.Service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public OperationTypeDTO getById(@PathVariable Integer id) {
        return operationTypeMapper.toDto(operationTypeService.getById(id));
    }

    @GetMapping
    public List<OperationTypeDTO> getAll(@RequestParam(required = false) Status status) {
        List<OperationType> types = (status != null)
                ? operationTypeService.getByStatus(status)
                : operationTypeService.getAll();
        return types.stream()
                .map(operationTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        operationTypeService.delete(id);
    }
}
