package com.generation.localpro.Controller;

import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.model.PortalUser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<PortalUserResponseDTO> BanUser(@PathVariable String userName) {
		return ResponseEntity.ok(uService.banUser(userName));
	}
}
