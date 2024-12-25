package tariq.mini.projet.service;


import tariq.mini.projet.dto.EtudiantDTO;
import tariq.mini.projet.model.Etudiant;
import tariq.mini.projet.repository.EtudiantRepository;
import tariq.mini.projet.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository repository;

    @Autowired
    private FiliereRepository filiereRepository;

    public List<EtudiantDTO> getAllEtudiants() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<EtudiantDTO> getEtudiantById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = toEntity(etudiantDTO);
        return toDTO(repository.save(etudiant));
    }

    public EtudiantDTO updateEtudiant(Long id, EtudiantDTO newEtudiantDTO) {
        return repository.findById(id).map(etudiant -> {
            etudiant.setName(newEtudiantDTO.getName());
            etudiant.setEmail(newEtudiantDTO.getEmail());
            if (newEtudiantDTO.getFiliereId() != null) {
                etudiant.setFiliere(filiereRepository.findById(newEtudiantDTO.getFiliereId())
                        .orElseThrow(() -> new RuntimeException("Filière non trouvée")));
            }
            return toDTO(repository.save(etudiant));
        }).orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
    }

    public void deleteEtudiant(Long id) {
        repository.deleteById(id);
    }

    // Mapper Entité -> DTO
    private EtudiantDTO toDTO(Etudiant etudiant) {
        EtudiantDTO dto = new EtudiantDTO();
        dto.setId(etudiant.getId());
        dto.setName(etudiant.getName());
        dto.setEmail(etudiant.getEmail());
        dto.setFiliereId(etudiant.getFiliere() != null ? etudiant.getFiliere().getId() : null);
        return dto;
    }

    // Mapper DTO -> Entité
    private Etudiant toEntity(EtudiantDTO dto) {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(dto.getId());
        etudiant.setName(dto.getName());
        etudiant.setEmail(dto.getEmail());
        if (dto.getFiliereId() != null) {
            etudiant.setFiliere(filiereRepository.findById(dto.getFiliereId())
                    .orElseThrow(() -> new RuntimeException("Filière non trouvée")));
        }
        return etudiant;
    }
}
