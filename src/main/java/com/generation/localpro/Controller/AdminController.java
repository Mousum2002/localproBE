package com.generation.localpro.Controller;

import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.dto.PortalUserResponseDTO;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/admin")
public class AdminController {
	private final PortalUserService uService;

	public AdminController(PortalUserService uService) {
		this.uService = uService;
	}
	@GetMapping
	public ResponseEntity<List<PortalUserResponseDTO>> getAll() {
		// returns okay always, even with empty list
		return ResponseEntity.ok(uService.getAll());
	}

	@PutMapping("ban/{userName}")
	public ResponseEntity<PortalUserResponseDTO> banUser(@PathVariable String userName) {
		return ResponseEntity.ok(uService.banUser(userName));
	}

	@DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        uService.delete(id); // Usa il metodo delete che abbiamo visto nel tuo PortalUserService
        return ResponseEntity.noContent().build(); // Restituisce 204 No Content (standard per delete)
    }
}
