package tariq.mini.projet.controller;

import tariq.mini.projet.dto.EtudiantDTO;
import tariq.mini.projet.model.Etudiant;
import tariq.mini.projet.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService service;

    @GetMapping
    public List<EtudiantDTO> getAllEtudiants() {
        return service.getAllEtudiants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDTO> getEtudiantById(@PathVariable Long id) {
        return service.getEtudiantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EtudiantDTO addEtudiant(@RequestBody EtudiantDTO etudiant) {
        return service.addEtudiant(etudiant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDTO> updateEtudiant(@PathVariable Long id, @RequestBody EtudiantDTO etudiant) {
        try {
            return ResponseEntity.ok(service.updateEtudiant(id, etudiant));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        service.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}
