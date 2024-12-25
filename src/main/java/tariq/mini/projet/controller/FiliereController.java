package tariq.mini.projet.controller;


import tariq.mini.projet.dto.FiliereDTO;
import tariq.mini.projet.model.Filiere;
import tariq.mini.projet.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {
    @Autowired
    private FiliereService service;

    @GetMapping
    public List<FiliereDTO> getAllFilieres() {
        return service.getAllFilieres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FiliereDTO> getFiliereById(@PathVariable Long id) {
        return service.getFiliereById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FiliereDTO addFiliere(@RequestBody FiliereDTO filiere) {
        return service.addFiliere(filiere);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FiliereDTO> updateFiliere(@PathVariable Long id, @RequestBody FiliereDTO filiere) {
        try {
            return ResponseEntity.ok(service.updateFiliere(id, filiere));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiliere(@PathVariable Long id) {
        service.deleteFiliere(id);
        return ResponseEntity.noContent().build();
    }
}
