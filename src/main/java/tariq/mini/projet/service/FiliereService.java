package tariq.mini.projet.service;



import tariq.mini.projet.dto.FiliereDTO;
import tariq.mini.projet.model.Filiere;
import tariq.mini.projet.model.Etudiant;
import tariq.mini.projet.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FiliereService {
    @Autowired
    private FiliereRepository repository;

    public List<FiliereDTO> getAllFilieres() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<FiliereDTO> getFiliereById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public FiliereDTO addFiliere(FiliereDTO filiereDTO) {
        Filiere filiere = toEntity(filiereDTO);
        return toDTO(repository.save(filiere));
    }

    public FiliereDTO updateFiliere(Long id, FiliereDTO newFiliereDTO) {
        return repository.findById(id).map(filiere -> {
            filiere.setName(newFiliereDTO.getName());
            return toDTO(repository.save(filiere));
        }).orElseThrow(() -> new RuntimeException("Filière non trouvée"));
    }

    public void deleteFiliere(Long id) {
        repository.deleteById(id);
    }

    // Mapper Entité -> DTO
    private FiliereDTO toDTO(Filiere filiere) {
        FiliereDTO dto = new FiliereDTO();
        dto.setId(filiere.getId());
        dto.setName(filiere.getName());
        dto.setEtudiants(filiere.getEtudiants().stream().map(Etudiant::getId).collect(Collectors.toList()));
        return dto;
    }

    // Mapper DTO -> Entité
    private Filiere toEntity(FiliereDTO dto) {
        Filiere filiere = new Filiere();
        filiere.setId(dto.getId());
        filiere.setName(dto.getName());
        return filiere;
    }
}
