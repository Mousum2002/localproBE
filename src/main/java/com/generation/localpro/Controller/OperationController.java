package com.generation.localpro.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.localpro.dto.OperationToListDTO;

import lombok.RequiredArgsConstructor;
import com.generation.localpro.Service.OperationTolistService;

import java.util.List;


@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {
     private final OperationTolistService operationService;

    @GetMapping
    public ResponseEntity<List<OperationToListDTO>> getAll() {
        return ResponseEntity.ok(operationService.getAllVendorOperations());
    }
}
