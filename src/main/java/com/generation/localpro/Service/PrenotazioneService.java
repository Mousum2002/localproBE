package com.generation.localpro.Service;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.generation.localpro.dto.PrenotazioneRequestDTO;
import com.generation.localpro.dto.PrenotazioneResponseDTO;
import com.generation.localpro.mapper.PrenotazioneMapper;
import com.generation.localpro.model.Prenotazione;
import com.generation.localpro.repository.OperationTypeByVendorRepository;
import com.generation.localpro.repository.PrenotazioneRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.generation.localpro.model.PortalUser;
import com.generation.localpro.model.OperationTypeByVendor;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final OperationTypeByVendorRepository serviceRepository;
    private final PrenotazioneMapper mapper;
    private final PortalUserService userService;
    private final OperationTypeByVendorService operationTypeByVendorService;

    public PrenotazioneResponseDTO create(PrenotazioneRequestDTO dto) {
        Prenotazione p = mapper.toEntity(dto);

        OperationTypeByVendor service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Servizio non trovato"));

        p.setVendor(service.getUser());
        p.setUser(userService.findByUserName(
                SecurityContextHolder.getContext().getAuthentication().getName()));
        p.setService(service);
        p.setReservationDate(LocalDateTime.now());
        p.setStatus("Creato");

        return mapper.toResponseDto(prenotazioneRepository.save(p));
    }

    public PrenotazioneResponseDTO updateStatus(int id, String status) {
        Prenotazione p = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        p.setStatus(status);
        return mapper.toResponseDto(prenotazioneRepository.save(p));
    }

    /**
     * Prenotazioni USCENTI: quelle che l'utente loggato ha fatto come CLIENTE
     * (visibile nella profile-page sotto "Servizi prenotati")
     */
    public List<PrenotazioneResponseDTO> getOutGoingPrenotazioni() {
        PortalUser user = getCurrentUser();
        return prenotazioneRepository.findAll().stream()
                .filter(p -> p.getUser() != null && p.getUser().getId() == user.getId())
                .map(mapper::toResponseDto)
                .toList();
    }

    /**
     * Prenotazioni ENTRANTI: quelle ricevute sui servizi che l'utente offre come VENDITORE
     * (visibile nella profile-page sotto "Prenotazioni ricevute")
     */
    public List<PrenotazioneResponseDTO> getIncomingPrenotazioni() {
        PortalUser user = getCurrentUser();
        return prenotazioneRepository.findAll().stream()
                .filter(p -> p.getVendor() != null && p.getVendor().getId() == user.getId())
                .map(mapper::toResponseDto)
                .toList();
    }

    // Mantenuto per retrocompatibilità
    public List<PrenotazioneResponseDTO> getPrenotazioni() {
        return getOutGoingPrenotazioni();
    }

    public void delete(Integer id) {
        Prenotazione p = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        p.setStatus("Cancellata");
        prenotazioneRepository.save(p);
    }

    private PortalUser getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUserName(userName);
    }
}