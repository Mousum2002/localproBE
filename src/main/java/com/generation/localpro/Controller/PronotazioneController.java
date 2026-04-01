package com.generation.localpro.Controller;

import org.springframework.web.bind.annotation.RestController;
import com.generation.localpro.dto.PrenotazioneRequestDTO;
import com.generation.localpro.dto.PrenotazioneResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.generation.localpro.Service.PrenotazioneService;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PronotazioneController {

    private final PrenotazioneService prenotazioneService;

    @PostMapping
    public ResponseEntity<PrenotazioneResponseDTO> creaPrenotazione(
            @RequestBody PrenotazioneRequestDTO prenotazione) {
        return ResponseEntity.ok(prenotazioneService.create(prenotazione));
    }

    // Prenotazioni USCENTI: quelle fatte dall'utente come cliente
    @GetMapping("/getOutoingPrenotazioni")
    public ResponseEntity<List<PrenotazioneResponseDTO>> getOutgoing() {
        return ResponseEntity.ok(prenotazioneService.getOutGoingPrenotazioni());
    }

    // Prenotazioni ENTRANTI: quelle ricevute sui servizi dell'utente come venditore
    @GetMapping("/getIncomingPrenotazioni")
    public ResponseEntity<List<PrenotazioneResponseDTO>> getIncoming() {
        return ResponseEntity.ok(prenotazioneService.getIncomingPrenotazioni());
    }

    @PutMapping("/update")
    public ResponseEntity<PrenotazioneResponseDTO> updatePrenotazione(
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(prenotazioneService.updateStatus(
                Integer.parseInt(body.get("id")), body.get("status")));
    }

    // Non cancella davvero — cambia lo status a "Cancellata"
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Integer id) {
        prenotazioneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}