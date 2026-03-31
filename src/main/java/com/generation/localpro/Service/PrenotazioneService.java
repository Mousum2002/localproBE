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
  
    public PrenotazioneResponseDTO create(PrenotazioneRequestDTO dto) {


    Prenotazione p = mapper.toEntity(dto);
    OperationTypeByVendor service =  serviceRepository.findById(dto.getServiceId())
            .orElseThrow(() -> new RuntimeException("Servizio non trovato"));

    PortalUser user = userService.findByUserName(SecurityContextHolder.getContext()
            .getAuthentication().getName());
    if (user == service.getUser()) {
        throw new IllegalArgumentException("Non poi prenotare te stesso");
    }
    p.setVendor(service.getUser());
 
    //user name dell'utente loggato
    p.setUser(user);

    
    p.setService(service);

    p.setReservationDate(LocalDateTime.now()); 
    p.setStatus("Creato");
    return mapper.toResponseDto(prenotazioneRepository.save(p));
    }
    
    public PrenotazioneResponseDTO updateStatus(int id, String status){
        String userName = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        
        Prenotazione p = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        
        return mapper.toResponseDto(prenotazioneRepository.save(p));
    }

    public List<PrenotazioneResponseDTO> getOutGoingPrenotazioni(){
        String userName = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        PortalUser user = userService.findByUserName(userName);
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAll().stream().filter((p)->p.getUser() == user).toList();
        return prenotazioni.stream().map(mapper::toResponseDto).toList();
    }

    public void delete(Integer id) {
        Prenotazione p =  prenotazioneRepository.findById(id).orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        p.setStatus("Cancellata");
        prenotazioneRepository.save(p);
    }

    public List<PrenotazioneResponseDTO> getIncomingPrenotazioni(){
        String userName = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        PortalUser user = userService.findByUserName(userName);
     List<Prenotazione> prenotazioni = prenotazioneRepository.findAll().stream().filter((p)->p.getVendor() == user).toList();
        return prenotazioni.stream().map(mapper::toResponseDto).toList();

    }

}
