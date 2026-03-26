package com.generation.localpro.Controller;

import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.model.PortalUser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {
	private final PortalUserService uService;

	public AdminController(PortalUserService uService) {
		this.uService = uService;
	}
	@GetMapping
	public ResponseEntity<List<PortalUser>> getAll() {
		// returns okay always, even with empty list
		return ResponseEntity.ok(uService.getAll());
	}
}
