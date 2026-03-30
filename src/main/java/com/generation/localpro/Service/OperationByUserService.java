package com.generation.localpro.Service;

import com.generation.localpro.model.OperationByUser;
import com.generation.localpro.model.OperationStatus;
import com.generation.localpro.model.OperationTypeByVendor;
import com.generation.localpro.model.PortalUser;
import com.generation.localpro.repository.OperationByUserRepository;
import com.generation.localpro.repository.OperationTypeByVendorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperationByUserService {

    private final OperationByUserRepository repository;
    private final PortalUserService userService;
    private final OperationTypeByVendorRepository vendorOpRepository;

    public OperationByUserService(
        OperationByUserRepository repository,
        PortalUserService userService,
        OperationTypeByVendorRepository vendorOpRepository
    ) {
        this.repository = repository;
        this.userService = userService;
        this.vendorOpRepository = vendorOpRepository;
    }

    // crea una prenotazione — il client è l'utente loggato
    public OperationByUser create(Integer operationByVendorId, String notes) {
        String currentUsername = SecurityContextHolder.getContext()
            .getAuthentication().getName();
        PortalUser client = userService.findByUserName(currentUsername);

        OperationTypeByVendor service = vendorOpRepository.findById(operationByVendorId)
            .orElseThrow(() -> new EntityNotFoundException("Servizio non trovato"));

        OperationByUser booking = new OperationByUser();
        booking.setClient(client);
        booking.setOperationTypeByVendor(service);
        booking.setRequestedAt(LocalDateTime.now());
        booking.setStatus(OperationStatus.PENDING);
        booking.setNotes(notes);

        return repository.save(booking);
    }

    // prenotazioni fatte da me come cliente
    public List<OperationByUser> getMyBookings() {
        String currentUsername = SecurityContextHolder.getContext()
            .getAuthentication().getName();
        PortalUser client = userService.findByUserName(currentUsername);
        return repository.findByClientId(client.getId());
    }

    // prenotazioni ricevute da me come professionista
    public List<OperationByUser> getReceivedBookings() {
        String currentUsername = SecurityContextHolder.getContext()
            .getAuthentication().getName();
        PortalUser vendor = userService.findByUserName(currentUsername);
        return repository.findByOperationTypeByVendorUserId(vendor.getId());
    }

    // aggiorna lo status (solo il professionista può confermare/completare)
    public OperationByUser updateStatus(Integer id, OperationStatus status) {
        OperationByUser booking = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        booking.setStatus(status);
        return repository.save(booking);
    }

    // cancella (solo il cliente può cancellare)
    public void cancel(Integer id) {
        OperationByUser booking = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        booking.setStatus(OperationStatus.CANCELLED);
        repository.save(booking);
    }
}