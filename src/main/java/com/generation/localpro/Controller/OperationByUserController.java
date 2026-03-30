package com.generation.localpro.Controller;

import com.generation.localpro.Service.OperationByUserService;
import com.generation.localpro.dto.OperationByUserDTO;
import com.generation.localpro.mapper.OperationByUserMapper;
import com.generation.localpro.model.OperationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class OperationByUserController {

    private final OperationByUserService service;
    private final OperationByUserMapper mapper;

    public OperationByUserController(
        OperationByUserService service,
        OperationByUserMapper mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationByUserDTO create(@RequestBody Map<String, Object> body) {
        Integer operationByVendorId = (Integer) body.get("operationByVendorId");
        String notes = (String) body.getOrDefault("notes", "");
        return mapper.toDto(service.create(operationByVendorId, notes));
    }

    @GetMapping("/mine")
    public List<OperationByUserDTO> getMyBookings() {
        return service.getMyBookings().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/received")
    public List<OperationByUserDTO> getReceivedBookings() {
        return service.getReceivedBookings().stream().map(mapper::toDto).toList();
    }

    @PatchMapping("/{id}/status")
    public OperationByUserDTO updateStatus(
        @PathVariable Integer id,
        @RequestBody Map<String, String> body
    ) {
        OperationStatus status = OperationStatus.valueOf(body.get("status"));
        return mapper.toDto(service.updateStatus(id, status));
    }

    @PatchMapping("/{id}/cancel")
    public void cancel(@PathVariable Integer id) {
        service.cancel(id);
    }
}